package com.hxgy.wechat.base;

/**
 * @author zy
 * @create 2018-01-18 22:12
 **/
public enum ResonseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LONGIN(10,"NEED_LOGIN"),

    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),
    NEED_BUY(11,"NEED_BUY"),
    NEED_PAY(12,"NEED_PAY")
    ;

    private int code;
    private String msg;

    ResonseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
