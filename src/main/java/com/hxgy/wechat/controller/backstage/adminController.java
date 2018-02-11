package com.hxgy.wechat.controller.backstage;

import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.entity.Admin;
import com.hxgy.wechat.service.backstage.IAdminService;
import com.hxgy.wechat.service.backstage.impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

/**
 * Created by 10104 on 2018/1/24.
 */
@Controller
@RequestMapping("/admin")
public class adminController {
    private static final Logger logger = LoggerFactory.getLogger(adminController.class);
    @Autowired
    private IAdminService iAdminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public int finduser(@RequestBody Admin admin, HttpSession session) {
        return iAdminService.finduser(admin.getName(), admin.getPassword(), session).getstatus();
    }

    @RequestMapping("/check")/*检查是否登录*/
    public ModelAndView redirect(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
//            return ""
            modelAndView.setViewName("ManagerLogin");
        } else {
            modelAndView.setViewName("indexHtml");
            modelAndView.addObject("adminName", session.getAttribute(Const.CURRENT_ADMIN));
        }
        return modelAndView;
    }

    @RequestMapping("/logOut")//退出登录
    public String logOut(HttpSession session) {
        session.removeAttribute(Const.CURRENT_ADMIN);
        return "ManagerLogout";
    }
}
