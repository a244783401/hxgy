package com.hxgy.wechat.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.hxgy.wechat.VO.RandomCodePtReqVo;
import com.hxgy.wechat.VO.ReqRandomCodeVo;
import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.config.SmsConfig;
import com.hxgy.wechat.repostory.UserDetailRepostory;
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
    @Autowired
    UserDetailRepostory userDetailRepostory;
    @Override
    public ServerResponse sendSms(ReqRandomCodeVo reqRandomCodeVo) throws Exception {
        RandomCodePtReqVo randomCodePtReqVo = new RandomCodePtReqVo();
        randomCodePtReqVo.setChannelType(Const.CHANNEL_CODE_APP);
        randomCodePtReqVo.setMsgDest(reqRandomCodeVo.getPhoneno());
        randomCodePtReqVo.setProductType("patient");
        if (reqRandomCodeVo.getType().equals("0")) {
            randomCodePtReqVo.setMsgType("msg");
            randomCodePtReqVo.setTemplateId(Const.RANDOM_CODE_MSG);
        } else {
            randomCodePtReqVo.setMsgType("voice");
            randomCodePtReqVo.setTemplateId(Const.RANDOM_CODE_VOICE);
        }
        if ("00".equals(reqRandomCodeVo.getIsRegister())) {
            if (userDetailRepostory.findByPhoneno(reqRandomCodeVo.getPhoneno()) != null) {
                return ServerResponse.createErrorMessage("该手机号已被注册");
            } else {
                String randomCode = getRandNum(6);
                randomCodePtReqVo.setMsgContent("您正在注册账号，验证码为:" + randomCode);
                String reqContent = JSONObject.toJSONString(randomCodePtReqVo);
                String routeCode = Const.ROUTE_CODE_RANDOM_CODE;
                String url = "";
                String[] params = {randomCode,"1"};
                SmsSingleSender ssender = new SmsSingleSender(smsConfig.getAppid(), smsConfig.getAppkey());
                SmsSingleSenderResult result =ssender.sendWithParam("86",reqRandomCodeVo.getPhoneno(),
                        smsConfig.getTemplateId(),params,"", "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
                if(result.result==0) {
                    setKey(reqRandomCodeVo.getPhoneno(), randomCode);
                    return ServerResponse.createSuccessMessage("验证码发送成功");
                }
                else {
                    return ServerResponse.createErrorMessage(result.errMsg);
                }
            }
        } else if ("01".equals(reqRandomCodeVo.getIsRegister())) {
            if (userDetailRepostory.findByPhoneno(reqRandomCodeVo.getPhoneno()) != null) {
                String randomCode = getRandNum(6);
                randomCodePtReqVo.setMsgContent("您正在尝试找回密码，验证码为：" + randomCode
                        + "\"}");
                String reqContent = JSONObject.toJSONString(randomCodePtReqVo);
                System.out.println(reqContent);
                String routeCode = Const.ROUTE_CODE_RANDOM_CODE;
                String url = "";
                String[] params = {randomCode,"1"};
                SmsSingleSender ssender = new SmsSingleSender(smsConfig.getAppid(), smsConfig.getAppkey());
                SmsSingleSenderResult result =ssender.sendWithParam("86",reqRandomCodeVo.getPhoneno(),
                        smsConfig.getTemplateId(),params,"", "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
                if(result.result==0) {
                    setKey(reqRandomCodeVo.getPhoneno(), randomCode);
                    return ServerResponse.createSuccessMessage("验证码发送成功");
                }
                else {
                    return ServerResponse.createErrorMessage(result.errMsg);
                }
            }

        }else {
            return ServerResponse.createErrorMessage("未知操作");
        }
        return ServerResponse.createErrorMessage("网络错误");
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
