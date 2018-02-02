package com.hxgy.wechat.service;

import com.hxgy.wechat.VO.ReqRandomCodeVo;
import com.hxgy.wechat.base.ServerResponse;

import javax.servlet.http.HttpSession;

/**
 * 验证码的使用
 * Created by zy on 2018/1/20.
 */
public interface ISmsService {
    /**
     *发送验证码到指定手机 并缓存验证码 10 min 及请求间隔时间
     * @param reqRandomCodeVo
     * @return
     */
    ServerResponse sendSms(ReqRandomCodeVo reqRandomCodeVo) throws Exception;
    /**
     *验证验证码
     * @param phoneNum,inputCode,randomCode
     * @return
     */
    ServerResponse validateSms(String phoneNum,String inputCode);


}
