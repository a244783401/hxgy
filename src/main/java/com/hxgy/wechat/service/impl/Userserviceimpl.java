package com.hxgy.wechat.service.impl;

import com.hxgy.wechat.VO.BowerObject;
import com.hxgy.wechat.VO.UserVo;
import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.UserDetail;
import com.hxgy.wechat.entity.UserEnrollCourse;
import com.hxgy.wechat.repostory.UserDetailRepostory;
import com.hxgy.wechat.repostory.UserEnrollCourseRepostory;
import com.hxgy.wechat.service.IUserService;
import com.hxgy.wechat.utils.MD5Util;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service("iUserService")
public class Userserviceimpl implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Userserviceimpl.class);
    @Autowired
    UserDetailRepostory  userDetailRepostory;
    @Autowired
    private UserEnrollCourseRepostory userEnrollCourseRepostory;

    public boolean judgePhoneNum(String phoneNum,Long userId){
        UserDetail userDetail = userDetailRepostory.findByPhoneno(phoneNum);
        if (userDetail != null && userDetail.getId() != userDetailRepostory.findOne(userId).getId()){
            return false;
        }
        return true;
    }

    public void updateUserImage(String imageUrl,Long userId){
        userDetailRepostory.updateUrlById(imageUrl,userId);
    }

    public void updateUser(BowerObject bowerObject,Long userId){
        UserDetail userDetail = userDetailRepostory.findOne(userId);
        userDetail.setPhoneno(bowerObject.getPhoneno());
        userDetail.setSex(bowerObject.getSex());
        userDetail.setName(bowerObject.getUsername());
        userDetail.setBirthDay(bowerObject.getBirthDay());
        userDetailRepostory.saveAndFlush(userDetail);
    }

    public ServerResponse addUserDetail(BowerObject bowerObject, Long userId){
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
    public ServerResponse getuserInfo(Long id) {
        UserDetail userDetail=userDetailRepostory.findOne(id);
        if(userDetail!=null) {
            return ServerResponse.isSuccess(getUserVo(userDetail));
        }
        return ServerResponse.isSuccess(null);
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
        userDetail.setLoginStatus("1");
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

    private UserVo getUserVo(UserDetail userDetail){
        if(userDetail!=null) {
            UserVo userVo = new UserVo();
            userVo.setId(userDetail.getId());
            if(userDetail.getSex()==null||userDetail.getSex()==Const.Sex.MALE.getCode()){
                userVo.setSex("男");
            }else {
                userVo.setSex("女");
            }
            if(userDetail.getBirthDay()==null){
                userVo.setBirthday("");
            }else {
                userVo.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(userDetail.getBirthDay()));
            }
            userVo.setName(userDetail.getName());
            userVo.setCop(userDetail.getCop());
            userVo.setEducation(userDetail.getEducation());
            userVo.setEmail(userDetail.getEmail());
            userVo.setHeadPortrait(userDetail.getHeadPortrait());
            userVo.setIdCard(userDetail.getIdCard());
            userVo.setPhoneno(userDetail.getPhoneno());
            userVo.setProfession(userDetail.getProfession());
            return userVo;
        }
        return null;
    }


}
