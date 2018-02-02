package com.hxgy.wechat.service;

import com.hxgy.wechat.base.ServerResponse;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import javax.servlet.http.HttpSession;

public interface IUserService {
    ServerResponse validateLogin(String phoneno, String password);
    ServerResponse validateLogin(WxMpUser wxMpUser);
    ServerResponse getuserInfo(HttpSession session);
    ServerResponse registerNew(String phoneno,String name,String password);
    ServerResponse editPassword(String phoneno,String password);
}
