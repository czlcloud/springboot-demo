package cn.czl.demo.common.system;

import cn.czl.demo.common.enums.HealthStateEnum;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;
import java.util.Iterator;

/**
 *
 * @Author: zack chen
 * @Date: 2022/12/1 15:21
 */
@Slf4j
@Component
public class GraceShutdownHook extends Thread{

    @Override
    public void run() {
        log.info("set health status down");
        HealthCache.getUniqueInstance().setGlobalStatus(HealthStateEnum.DOWN);
        log.info("shutdown hook GraceShutdownHook begin");
        log.info("TODO: add your busi here. you can also manage app hooks in java.lang.ApplicationShutdownHooks.hooks. e.g. run it ahead.");
        try {
            Class<?> cls = Class.forName("java.lang.ApplicationShutdownHooks");
            Field appHooks = cls.getDeclaredField("hooks");
            appHooks.setAccessible(true);
            IdentityHashMap<Thread, Thread> identityHashMap = (IdentityHashMap<Thread, Thread>) appHooks.get(cls);
            Iterator<Thread> iterator = identityHashMap.keySet().iterator();
            while (iterator.hasNext()) {
                Thread next = iterator.next();
                log.debug("app hook---" + next.getName());
                if(StringUtils.equals(next.getName(), "DubboShutdownHook")){
                    log.debug("found DubboShutdownHook, run it now and remove it from ApplicationShutdownHooks.hook");
                    iterator.remove();
                    next.start();

                }
            }

            log.info("gracefully waiting for app running begin");
            ThreadUtil.safeSleep(30000);
            log.info("gracefully wait for app running finished for 30s. app shutdown now");

        } catch (Exception e) {
            log.error("GraceShutdownHook error", e);
        }

    }
}
