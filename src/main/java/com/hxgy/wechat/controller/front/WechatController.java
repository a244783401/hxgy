package com.hxgy.wechat.controller.front;


import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.config.WechatAccountConfig;
import com.hxgy.wechat.service.IUserService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

/**
 * 微信登陆
 *
 * @author wx
 * @create 2018-01-25 14:00
 **/
@Controller
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private WechatAccountConfig wechatAccountConfig;

/**
 * 获取code
 *
 * */
    @RequestMapping("/getcode")
    public String getcode()  {
        StringBuilder url=new StringBuilder();
        url.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
        url.append(wechatAccountConfig.getMpAppId());
        url.append("&redirect_uri="+wechatAccountConfig.getNotifyUrl()+"/wechat/authorize");
        url.append("&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");
        return "redirect:"+url;
    }

    /**
     * 根据code获取授权
     *
     * */
    @RequestMapping("/authorize")
    public String authorize(@RequestParam("code") String code)  throws WxErrorException {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=wxMpService.oauth2getAccessToken(code);
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return"redirect:"+wechatAccountConfig.getNotifyUrl()+"/wechat/login?openId="+openId;
    }

    /**
     * 微信登陆
     *
     * */
    @RequestMapping("/login")
    public String userinfo(@RequestParam("openId") String openId,HttpSession session) throws WxErrorException {
        WxMpUser wxMpUser=wxMpService.getUserService().userInfo(openId);
        ServerResponse serverResponse=iUserService.validateLogin(wxMpUser);
        if(serverResponse.getstatus()==0){
            session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
        }
        return "redirect:"+wechatAccountConfig.getNotifyUrl()+"/user/login/myCenterIndex";
    }

}




