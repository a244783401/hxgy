package com.hxgy.wechat.service.impl;

import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.service.ISmsService;

/**
 * 短信验证实现类
 *
 * @author zy
 * @create 2018-01-20 15:07
 **/
public class SmsServiceimpl implements ISmsService{
    @Override
    public ServerResponse<String> sendSms(String phoneNum) {
        return null;
    }

    @Override
    public String getSmsCode(String phoneNum) {
        return null;
    }

    @Override
    public void remove(String phoneNum) {

    }
}
