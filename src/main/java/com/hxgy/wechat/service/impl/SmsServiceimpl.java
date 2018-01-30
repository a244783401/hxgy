package com.hxgy.wechat.service.impl;

import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.config.SmsConfig;
import com.hxgy.wechat.service.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.hxgy.wechat.utils.RandomNumUtil.getRandNum;
import static com.hxgy.wechat.utils.TokenCacheUtil.getKey;
import static com.hxgy.wechat.utils.TokenCacheUtil.setKey;

/**
 * 短信验证实现类
 *
 * @author zy
 * @create 2018-01-20 15:07
 **/
@Service("iSmsService")
public class SmsServiceimpl implements ISmsService{
    @Autowired
    SmsConfig smsConfig;
    @Override
    public ServerResponse sendSms(String phoneNum) {
        String randomCode = getRandNum(6);
        setKey(phoneNum,randomCode);
        System.out.println(randomCode);
       /* try {
            SmsSingleSender ssender = new SmsSingleSender(smsConfig.getAppid(),smsConfig.getAppkey());
            SmsSingleSenderResult result = ssender.send(0, "86", phoneNum,
                    "【华西公用】您的验证码是:"+randomCode, "", "");*/
            return ServerResponse.createSuccessMessage("验证码发送成功");
       /* } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
            return ServerResponse.createErrorMessage("HTTP响应码错误!");
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            return ServerResponse.createErrorMessage("网络IO错误!");
        }*/
    }

    @Override
    public ServerResponse validateSms(String phoneNum, String inputCode) {
        String randomCode= getKey(phoneNum);
            if(inputCode.trim().equals(randomCode.trim())){
                return ServerResponse.createSuccess();
            }
            else {
                return ServerResponse.createErrorMessage("验证码错误!");
            }
    }



}
