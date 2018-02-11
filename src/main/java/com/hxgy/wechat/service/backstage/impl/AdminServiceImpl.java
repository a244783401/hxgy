package com.hxgy.wechat.service.backstage.impl;

import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.Admin;
import com.hxgy.wechat.repostory.AdminRepostory;
import com.hxgy.wechat.service.backstage.IAdminService;
import com.hxgy.wechat.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by 10104 on 2018/1/24.
 */
@Service("iAdminService")
public class AdminServiceImpl implements IAdminService{
    @Autowired
    private AdminRepostory adminRepostory;
    @Override
    public ServerResponse finduser(String username, String password,HttpSession session){
        Admin admin = adminRepostory.findByName(username);
        if (admin == null){
            return ServerResponse.createErrorMessage("404");
        }else if (!MD5Util.MD5EncodeUtf8(password).equals(admin.getPassword())){
            return ServerResponse.createErrorMessage("500");
        }
        session.setAttribute(Const.CURRENT_ADMIN,username);
        return ServerResponse.createSuccess();
    }
}
