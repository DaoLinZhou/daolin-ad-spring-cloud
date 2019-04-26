package com.daolin.ad.common;

/**
 * Created by Daolin
 */
public enum ResponseCode {

    SUCCESS(0, "success"),
    ERROR(-10, "error"),
    AD_EXCEPTION(-1, "business exception")
    ;

    private int code;
    private String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }

}
