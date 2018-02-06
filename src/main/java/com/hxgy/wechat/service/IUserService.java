package com.hxgy.wechat.service;

import com.hxgy.wechat.VO.BowerObject;
import com.hxgy.wechat.base.ServerResponse;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import javax.servlet.http.HttpSession;

public interface IUserService {
    ServerResponse validateLogin(String phoneno, String password);
    ServerResponse validateLogin(WxMpUser wxMpUser);
    ServerResponse getuserInfo(Long id);
    ServerResponse registerNew(String phoneno, String name, String password);
    ServerResponse editPassword(String phoneno, String password);
    ServerResponse addUserDetail(BowerObject bowerObject, Long userId);
    void updateUserImage(String imageUrl,Long userId);
    void updateUser(BowerObject bowerObject,Long userId);
    boolean judgePhoneNum(String phoneNum,Long userId);
}
