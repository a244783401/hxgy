package com.hxgy.wechat.base;

/**
 * 返回类
 * @author zy
 * @create 2018-01-18 22:06
 **/
public class ServerResponse<T> {
    private int status;
    private String message;
    private T data;

    public ServerResponse() {
    }

    public ServerResponse(int status) {
        this.status = status;
    }

    public ServerResponse(T data, int status) {
        this.data = data;
        this.status = status;
    }

    public ServerResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public ServerResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getstatus() {
        return status;
    }

    public void setstatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private boolean getSuccess(){
        return this.status == ResonseCode.SUCCESS.getCode();
    }


    public static<T> ServerResponse<T> createSuccess(){
        return new ServerResponse(ResonseCode.SUCCESS.getCode());
    }
    public static<T> ServerResponse<T> createSuccessMessage(String msg){
        return new ServerResponse(msg,ResonseCode.SUCCESS.getCode());
    }
    public static<T> ServerResponse<T> isSuccess(T data){
        return new ServerResponse(data,ResonseCode.SUCCESS.getCode());
    }
    public static<T> ServerResponse<T> isSuccess(String msg,T data){
        return new ServerResponse(ResonseCode.SUCCESS.getCode(),msg,data);
    }

    public static<T> ServerResponse<T> createError(){
        return new ServerResponse(ResonseCode.ERROR.getCode());
    }
    public static<T> ServerResponse<T> createErrorMessage(String errormsg){
        return new ServerResponse(errormsg,ResonseCode.ERROR.getCode());
    }
    public static<T> ServerResponse<T> createErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorMessage,errorCode);
    }
}
