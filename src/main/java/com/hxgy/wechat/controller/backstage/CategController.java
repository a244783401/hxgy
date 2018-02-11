package com.hxgy.wechat.controller.backstage;

import javax.servlet.http.HttpServletResponse;

import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthCategory;
import com.hxgy.wechat.service.backstage.ICategService;
import com.hxgy.wechat.service.backstage.impl.CategServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by 10104 on 2018/1/29.
 */
@Controller
@RequestMapping("/admin")
public class CategController {

    @Autowired

    private ICategService iCategService;

    private static final Logger logger = LoggerFactory.getLogger(CategController.class);

    @RequestMapping("/categlist")//查所有分类
    @ResponseBody
    public ServerResponse searchCategories(HttpSession session, HttpServletResponse response) {
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else
            return iCategService.findAllCategories();
    }

    @RequestMapping("/categIndex")//跳转分类页面
    public String gotocategIndex(HttpSession session, HttpServletResponse response) {
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else return "categManager";
    }

    @RequestMapping(value = "/addCateg", method = RequestMethod.POST)//添加分类
    @ResponseBody
    public ServerResponse addCateg(@RequestBody HealthCategory healthCategory, HttpSession session, HttpServletResponse response) {
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else
            return iCategService.insertCateg(healthCategory.getCode(), healthCategory.getCategoryDesc(), healthCategory.getVersion(), healthCategory.getStand(),healthCategory.getPush());
    }

    @RequestMapping(value = "/updateCateg", method = RequestMethod.POST)//更新分类
    @ResponseBody
    public ServerResponse updateCateg(@RequestBody HealthCategory healthCategory, HttpSession session, HttpServletResponse response) throws Exception {
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            response.sendRedirect("check");
            return null;
        } else
            return iCategService.upDateCateg(healthCategory.getId(), healthCategory.getCode(), healthCategory.getCategoryDesc(), healthCategory.getVersion(), healthCategory.getStand(),healthCategory.getPush());
    }

    @RequestMapping(value = "/deleteCateg", method = RequestMethod.POST)//删除分类
    @ResponseBody
    public ServerResponse deleteCateg(@RequestBody String ids, HttpSession session, HttpServletResponse response) {
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return iCategService.deleteCateg(ids);
        }
    }
}
