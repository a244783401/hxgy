package com.hxgy.wechat.controller.front;


import com.hxgy.wechat.VO.BowerObject;
import com.hxgy.wechat.VO.UserVo;
import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ResonseCode;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.UserDetail;
import com.hxgy.wechat.service.ISmsService;
import com.hxgy.wechat.service.IUserService;
import com.hxgy.wechat.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

import static com.hxgy.wechat.utils.TokenCacheUtil.getKey;

/**
 * 用户登陆
 *
 * @author wx
 * @create 2018-01-23 14:00
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iUserService;
    @Autowired
    ISmsService iSmsService;

    /**
     * 普通登陆
     *
     **/
    @RequestMapping(method = RequestMethod.POST,value = "/login/userLogin_psy")
    @ResponseBody
    public ServerResponse userLogin(@RequestParam(value = "phoneno") String phoneno,
                                    @RequestParam(value = "password") String password,
                                    HttpSession session)
    {
        if(phoneno!=""&&password!="") {
            ServerResponse<UserDetail> serverResponse = iUserService.validateLogin(phoneno,password);
            if (serverResponse.getstatus()==0) {
                session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
            }
            return serverResponse;
        }
       return ServerResponse.createErrorMessage("请输入账号密码!");
    }
    /**
     * 获取用户信息
     *
     **/
    @RequestMapping("/login/getuserInfo")
    @ResponseBody
    public ServerResponse getuserInfo(HttpSession session){
        if(session.getAttribute(Const.CURRENT_USER)!=null) {
            UserDetail userDetail=(UserDetail)session.getAttribute(Const.CURRENT_USER);
            Long id=userDetail.getId();
            ServerResponse<UserVo> serverResponse = iUserService.getuserInfo(id);
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
                return serverResponse;
        }
        return ServerResponse.createErrorMessage("请先登陆！");
    }
    /**
     * 登出
     *
     **/
    @RequestMapping("/login/logout")
    @ResponseBody
    public ServerResponse logout(HttpSession session){
        if(session.getAttribute(Const.CURRENT_USER)!=null){
            session.removeAttribute(Const.CURRENT_USER);
            return ServerResponse.createSuccessMessage("注销成功!");
        }
        else {
            return ServerResponse.createErrorMessage("请先登陆!");
        }

    }
    /**
     * 修改信息
     *
     **/


    /**
     * 找回密码
     *
     **/
    @RequestMapping("/login/findPassword")
    @ResponseBody
    public ServerResponse findPassword(@RequestParam(value = "phoneno") String phoneno,
                                       @RequestParam(value = "randomCode") String randomCode,
                                       @RequestParam(value = "channel") String channel,
                                       @RequestParam(value = "newPassword") String newPassword,
                                       HttpSession session)
    {
        if("psy".equals(channel)) {
            if (getKey(phoneno) != null) {
                ServerResponse serverResponse = iUserService.editPassword(phoneno, newPassword);
                if (serverResponse.getstatus() == 0) {
                    session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
                }
                return serverResponse;
            } else {
                return ServerResponse.createErrorMessage("验证码已过期!");
            }
        }
        return ServerResponse.createErrorMessage("未知渠道!");

    }


    /**
     * 注册新用户
     *
     *
     * */

    @RequestMapping("/register/registerUser")
    @ResponseBody
    public ServerResponse registerUser(@RequestParam(value = "phoneno") String phoneno,
                                           @RequestParam(value = "channel") String channel,
                                           @RequestParam(value = "randomCode") String randomCode,
                                           @RequestParam(value = "password") String password,
                                           @RequestParam(value = "username") String name,
                                           HttpSession session) {
            if (phoneno != null && password != null && name != null && randomCode != null && channel != null) {
                ServerResponse<UserDetail> serverResponse = iUserService.registerNew(phoneno, name, password);
                if (serverResponse.getstatus() == 0) {
                    session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
                }
                return serverResponse;
            }
            else {
                return ServerResponse.createErrorMessage("输入信息有误!");
            }
        }
    @RequestMapping("/enterForUser")
    @ResponseBody
    public ServerResponse enterForUser(@RequestParam("phoneno") String phoneno,
                                       @RequestParam("username") String username,
                                       @RequestParam("sex") String sex,
                                       @RequestParam("birthDay") String birthDay,
                                       HttpSession session
                                        ){
        UserDetail userDetail = (UserDetail) session.getAttribute(Const.CURRENT_USER);
        if (userDetail== null){
            return ServerResponse.createErrorCodeMessage(ResonseCode.NEED_LOGIN.getCode(),ResonseCode.NEED_LOGIN.getMsg());
        }
        BowerObject bowerObject = new BowerObject();
        bowerObject.setBirthDay(DateTimeUtil.strToDate(birthDay,"yyyy-MM-dd"));
        bowerObject.setPhoneno(phoneno);
        if (sex.equals("男")){
            bowerObject.setSex(Const.Sex.MALE.getCode());
        }else bowerObject.setSex(Const.Sex.FALEMALE.getCode());
        iUserService.updateUser(bowerObject,userDetail.getId());
        return ServerResponse.createSuccess();
    }
    @RequestMapping("/judge")
    @ResponseBody
    public ServerResponse judge(@RequestParam("phoneNum") String phoneNum,HttpSession session){
        UserDetail userDetail = (UserDetail)session.getAttribute(Const.CURRENT_USER);
        if (iUserService.judgePhoneNum(phoneNum,userDetail.getId())){
            return ServerResponse.createSuccess();
        }else return ServerResponse.createError();
    }



}
