package cn.czl.demo.common.system;

import cn.czl.demo.common.enums.HealthStateEnum;
import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author: zack chen
 * @Date: 2022/12/2 11:25
 */
public class HealthCache {

    private volatile static ConcurrentMap<String, HealthStateEnum> details;

    private volatile static HealthCache healthCacheObj;

    private HealthCache(){}

    public static HealthCache getUniqueInstance(){
        if (null == healthCacheObj) {
            synchronized (HealthCache.class){
                if (null == healthCacheObj) {
                    healthCacheObj = new HealthCache();
                    details = new ConcurrentHashMap<>();
                    healthCacheObj.setGlobalStatus(HealthStateEnum.DOWN);
                }
            }
        }
        return healthCacheObj;
    }

    /**
     * 延迟指定时间后，设置全局健康状态
     *
      */
    public void delaySetGlobalStatus(HealthStateEnum statusEnum, long millis){

        ThreadUtil.execute(()->{
                ThreadUtil.safeSleep(millis);
                setGlobalStatus(statusEnum);
        });
    }

    public HealthStateEnum getGlobalStatus() {
        if (!details.containsKey("global")){
            return HealthStateEnum.DOWN;
        }
        return details.get("global");
    }

    public void setGlobalStatus(HealthStateEnum status) {
        if (null == status){
            status = HealthStateEnum.UNKNOWN;
        }
        details.put("global",status);

    }

    public static String getState(){
        return getUniqueInstance().getGlobalStatus().getState();
    }

}
