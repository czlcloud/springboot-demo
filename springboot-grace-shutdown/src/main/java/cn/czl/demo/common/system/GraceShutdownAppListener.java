package cn.czl.demo.common.system;

import cn.czl.demo.common.enums.HealthStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.IdentityHashMap;
import java.util.Iterator;

/**
 * @Author: zack chen
 * @Date: 2022/11/11 11:23
 */
@Slf4j
@Component
public class GraceShutdownAppListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

            log.info("-------GraceShutdownAppListener ContextRefreshedEvent  grace shut down init--------");

        try {
            // 拿到字段
            Class<?> sc = Class.forName("java.lang.Shutdown");
            Method method = sc.getDeclaredMethod("add", int.class, boolean.class, Runnable.class);
            method.setAccessible(true);
            // 插入在ApplicationShutdownHooks之前，前提是slot=0没被占用
            method.invoke(null, 0, false, new GraceShutdownHook());
            // 调试打印hook
            if (log.isDebugEnabled()){
                // 打印hook
                Field hooks = sc.getDeclaredField("hooks");
                hooks.setAccessible(true);
                Runnable[] runnables = ((Runnable[]) hooks.get(null));
                for (int i = 0; i <runnables.length; i++) {
                    if (log.isDebugEnabled() && null != runnables[i]){
                        log.debug(i + "------" + runnables[i].getClass().toString());

                    }
                }
                // 打印应用hook
                log.debug("check app shutdownhook");
                Class<?> cls = Class.forName("java.lang.ApplicationShutdownHooks");
                Field appHooks = cls.getDeclaredField("hooks");
                appHooks.setAccessible(true);
                IdentityHashMap<Thread, Thread> identityHashMap = (IdentityHashMap<Thread, Thread>) appHooks.get(cls);
                Iterator<Thread> iterator = identityHashMap.keySet().iterator();
                while (iterator.hasNext()) {
                    Thread next = iterator.next();
                    log.debug("app hook---" + next.getName());
                }
            }

            //等待时间：Spring容器初始化完成到服务启动时间
            HealthCache.getUniqueInstance().delaySetGlobalStatus(HealthStateEnum.UP, 1000L);
        } catch (Exception e) {
            log.error("grace shutdown failed.", e.getMessage());
        }
    }
}
