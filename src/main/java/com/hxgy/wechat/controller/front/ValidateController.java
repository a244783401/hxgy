package com.hxgy.wechat.controller.front;

import com.hxgy.wechat.VO.ReqRandomCodeVo;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.service.ISmsService;
import com.hxgy.wechat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.hxgy.wechat.utils.TokenCacheUtil.getKey;


@Controller
@RequestMapping("/validate")
public class ValidateController {
    @Autowired
    IUserService iUserService;
    @Autowired
    ISmsService iSmsService;
    /**
     * 验证验证码
     *
     * */
    @RequestMapping("/validateRandomCode")
    @ResponseBody
    public ServerResponse validateRandomCode(@RequestParam(value = "phoneno") String phoneno,
                                             @RequestParam(value = "inputCode") String inputCode,
                                             @RequestParam(value = "codeType") String codeType){
        if(phoneno!=null&&inputCode!=null){
            if(getKey(phoneno)==null)
            {
                return ServerResponse.createErrorMessage("验证码已失效!");
            }
            else {
                return iSmsService.validateSms(phoneno,inputCode);
            }

        }
        else {
            return ServerResponse.createErrorMessage("电话或验证码不能为空!");
        }

    }

    /**
     * 获取验证码
     *
     * */
    @RequestMapping(value = "/generateRandomCode")
    @ResponseBody
    public ServerResponse generateRandomCode(@RequestParam(value = "phoneno") String phoneno,
                                             @RequestParam(value = "channel") String channel,
                                             @RequestParam(value = "isRegister") String isRegister,
                                             @RequestParam(value = "type") String type) throws Exception {
        ReqRandomCodeVo reqRandomCodeVo=new ReqRandomCodeVo();
        reqRandomCodeVo.setPhoneno(phoneno);
        reqRandomCodeVo.setChannel(channel);
        reqRandomCodeVo.setIsRegister(isRegister);
        reqRandomCodeVo.setType(type);
        return iSmsService.sendSms(reqRandomCodeVo);

    }

}
