package com.hxgy.wechat.controller.backstage;

import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthCourseItem;
import com.hxgy.wechat.service.backstage.IVideoManagerService;
import com.hxgy.wechat.service.backstage.impl.VideoManagerServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 10104 on 2018/1/29.
 */
@Controller
@RequestMapping("/admin")
public class VideoMangerController {
    @Autowired
    private  IVideoManagerService iVideoManagerService;

    @RequestMapping("/videoIndex")//跳转视频页面
    public String gotoVideoIndex(HttpSession session, HttpServletResponse response) {
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else return "videoManager";
    }

    @RequestMapping("/videoList")//视频列表
    @ResponseBody
    public ServerResponse findAllVideos(HttpSession session, HttpServletResponse response) {
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else return iVideoManagerService.findAllVideos();
    }

    @RequestMapping(value = "/updateVideo", method = RequestMethod.POST)//更新视频
    @ResponseBody
    public ServerResponse updateVideo(@RequestBody HealthCourseItem healthCourseItem, HttpSession session, HttpServletResponse response) {
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else
            return iVideoManagerService.updateVideo(healthCourseItem);
    }

    @RequestMapping(value = "/addVideo", method = RequestMethod.POST)//添加视频
    @ResponseBody
    public ServerResponse addVideo(@RequestBody HealthCourseItem healthCourseItem, HttpSession session, HttpServletResponse response) {
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else
            return iVideoManagerService.addVideo(healthCourseItem);
    }

    @RequestMapping(value = "/deleteVideo", method = RequestMethod.POST)//删除视频
    @ResponseBody
    public ServerResponse deleteVideo(@RequestBody String ids, HttpSession session, HttpServletResponse response) {
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return iVideoManagerService.deleteVideo(ids);
        }
    }
}
