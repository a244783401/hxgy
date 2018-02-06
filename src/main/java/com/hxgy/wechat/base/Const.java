package com.hxgy.wechat.base;

/**
 * @author zy
 * @create 2018-01-23 17:01
 **/
public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String RANDOM_CODE="randomCode";
    public static final int NOT_PAY = 100;
    public static final int BOUGHT = 200;
    public static final int FREE = 11;
    public static final int NO_FREE = 12;
    public static final String CHANNEL_CODE_APP="HXGYAPP";
    public static final String RANDOM_CODE_MSG="9FB5EB88099FEA41069CC0F565719DC9";
    public static final String RANDOM_CODE_VOICE="94153951891C905D991ED076B3F3177D";
    public static final String ROUTE_CODE_RANDOM_CODE="commonSendMsg";
    public static final String PT_RES_SUCCESS="00";
    public enum Sex{

        MALE(0,"男性"),
        FALEMALE(1,"女性")
        ;
        Sex(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        private int code;
        private String msg;

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
}
