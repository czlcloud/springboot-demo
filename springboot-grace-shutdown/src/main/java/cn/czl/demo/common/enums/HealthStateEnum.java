package cn.czl.demo.common.enums;

/**
 * @Author: zack chen
 * @Date: 2022/12/3 10:01
 */
public enum HealthStateEnum {

    UP("Up"),
    DOWN("Down"),
    UNKNOWN("Unknown"),

    READY("Ready"),
    NOT_READY("NotReady"),

    ;
    private String state;

    private HealthStateEnum(){}

    HealthStateEnum(String state){
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
