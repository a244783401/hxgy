package com.hxgy.wechat.service.impl;

import com.hxgy.wechat.VO.LoginInfoVo;
import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.UserDetail;
import com.hxgy.wechat.repostory.UserDetailRepostory;
import com.hxgy.wechat.service.IUserService;
import com.hxgy.wechat.utils.MD5Util;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;


@Service("iUserService")
public class Userserviceimpl implements IUserService {
    @Autowired
    UserDetailRepostory  userDetailRepostory;

    @Override
    public ServerResponse validateLogin(String phoneno, String password) {
         UserDetail userDetail=userDetailRepostory.findByNameOrPhoneno(phoneno,phoneno);
         if(userDetail!=null) {
             if (userDetail.getPassword().equals(MD5Util.MD5EncodeUtf8(password))) {
                 return ServerResponse.isSuccess("登陆成功!", userDetail);
             }
             else {
                 return ServerResponse.createErrorMessage("用户或密码错误!");
             }
         }
         else {
             return ServerResponse.createErrorMessage("用户不存在!");
         }
    }

    @Override
    public ServerResponse validateLogin(WxMpUser wxMpUser) {

      UserDetail userDetail=userDetailRepostory.findByOpenId(wxMpUser.getOpenId());
      if(userDetail==null){
          UserDetail newuser= new UserDetail();
          newuser.setOpenId(wxMpUser.getOpenId());
          newuser.setLoginStatus("2");
          newuser.setName(wxMpUser.getNickname());
          newuser.setHeadPortrait(wxMpUser.getHeadImgUrl());
          userDetailRepostory.save(newuser);
          return ServerResponse.isSuccess(newuser);
      }
          return ServerResponse.isSuccess(userDetail);
    }

    @Override
    public ServerResponse getuserInfo(HttpSession session) {
        UserDetail userDetail= (UserDetail) session.getAttribute(Const.CURRENT_USER);
        return ServerResponse.isSuccess(toObjVO(userDetail));
    }

    @Override
    public ServerResponse registerNew(String phoneno, String name, String password) {
        if(userDetailRepostory.findByPhoneno(phoneno)!=null){
            return ServerResponse.createErrorMessage("该手机已被注册!");
        }
        if(userDetailRepostory.findByName(name)!=null){
            return ServerResponse.createErrorMessage("该用户名已被注册!");
        }
        UserDetail userDetail=new UserDetail();
        userDetail.setName(name);
        userDetail.setPhoneno(phoneno);
        userDetail.setPassword(MD5Util.MD5EncodeUtf8(password));
        userDetailRepostory.save(userDetail);
        return  ServerResponse.isSuccess("注册成功!",userDetail);
    }


    private LoginInfoVo toObjVO(UserDetail userDetail){
        LoginInfoVo loginInfoVo=new LoginInfoVo();
        if(userDetail!=null){
            loginInfoVo.setName(userDetail.getName());
            loginInfoVo.setHeadPortrait(userDetail.getHeadPortrait());
            return loginInfoVo;
        }
        else {
            return null;
        }

    }


}
