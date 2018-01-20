package com.hxgy.wechat.service;

import com.hxgy.wechat.base.ServerResponse;

/**
 * 验证码的使用
 * Created by zy on 2018/1/20.
 */
public interface ISmsService {
    /**
     *发送验证码到指定手机 并缓存验证码 10 min 及请求间隔时间
     * @param phoneNum
     * @return
     */
    ServerResponse<String> sendSms(String phoneNum);

    /**
     * 获取缓存中的验证码
     * @param phoneNum
     * @return
     */
    String getSmsCode(String phoneNum);

    /**
     * 移除指定缓存中的验证码
     * @param phoneNum
     */
    void remove(String phoneNum);
}
