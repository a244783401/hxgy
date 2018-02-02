package com.hxgy.wechat.service.impl;

import com.hxgy.wechat.VO.BowerObject;
import com.hxgy.wechat.VO.LoginInfoVo;
import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.UserDetail;
import com.hxgy.wechat.entity.UserEnrollCourse;
import com.hxgy.wechat.repostory.UserDetailRepostory;
import com.hxgy.wechat.repostory.UserEnrollCourseRepostory;
import com.hxgy.wechat.service.IUserService;
import com.hxgy.wechat.utils.MD5Util;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service("iUserService")
public class Userserviceimpl implements IUserService {
    @Autowired
    UserDetailRepostory  userDetailRepostory;
    @Autowired
    private UserEnrollCourseRepostory userEnrollCourseRepostory;

    public void updateUserImage(String imageUrl,Long userId){
        UserDetail userDetail = userDetailRepostory.findOne(userId);
        userDetail.setHeadPortrait(imageUrl);
        userDetailRepostory.save(userDetail);
    }

    public ServerResponse addUserDetail(BowerObject bowerObject,Long userId){
        UserEnrollCourse userEnrollCourse = new UserEnrollCourse();
        userEnrollCourse.setCourseId(Long.parseLong(bowerObject.getCourseid()));
        userEnrollCourse.setUserId(userId);
        userEnrollCourse.setPay(Const.NOT_PAY);
        userEnrollCourse.setOrderNo((int)System.currentTimeMillis());
        userEnrollCourse.setOrderDate(new Date());
        UserDetail userDetail = userDetailRepostory.findOne(userId);
        userDetail.setRealName(bowerObject.getUsername());
        userDetail.setProfession(bowerObject.getProfession());
        userDetail.setHeadPortrait(bowerObject.getHeadPortrait());
        userDetail.setIdCard(bowerObject.getIdCard());
        userDetail.setEducation(bowerObject.getEducation());
        userDetail.setCop(bowerObject.getCop());
        userDetail.setPhoneno(bowerObject.getPhoneno());
        userDetail.setEmail(bowerObject.getEmail());
        userDetailRepostory.save(userDetail);
        UserEnrollCourse enrollCourse = userEnrollCourseRepostory.save(userEnrollCourse);
        return ServerResponse.isSuccess(enrollCourse);
    }

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
        if(userDetail!=null) {
            Long id = userDetail.getId();
            UserDetail currentUser = userDetailRepostory.findOne(id);
            if (!userDetail.equals(currentUser)) {
                session.setAttribute(Const.CURRENT_USER, currentUser);
            }
            return ServerResponse.isSuccess(toObjVO(currentUser));
        }
        return ServerResponse.isSuccess(toObjVO(userDetail));
    }

    @Override
    public ServerResponse registerNew(String phoneno, String name, String password) {
        if(userDetailRepostory.findByName(name)!=null){
            return ServerResponse.createErrorMessage("该用户名已被注册!");
        }
        if(userDetailRepostory.findByPhoneno(phoneno)!=null){
            return ServerResponse.createErrorMessage("该手机已被注册!");
        }
        UserDetail userDetail=new UserDetail();
        userDetail.setName(name);
        userDetail.setPhoneno(phoneno);
        userDetail.setPassword(MD5Util.MD5EncodeUtf8(password));
        userDetailRepostory.save(userDetail);
        return  ServerResponse.isSuccess("注册成功!",userDetail);
    }


    @Override
    public ServerResponse editPassword(String phoneno, String newPassword) {
        UserDetail userDetail=userDetailRepostory.findByPhoneno(phoneno);
        if(userDetail==null){
            return ServerResponse.createErrorMessage("该手机未被注册!");
        }
        else {
            userDetail.setPassword(MD5Util.MD5EncodeUtf8(newPassword));
            userDetailRepostory.save(userDetail);
            return ServerResponse.isSuccess("修改成功!请重新登陆",userDetail);
        }
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
