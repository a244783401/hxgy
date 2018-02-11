package com.hxgy.wechat.controller.backstage;

import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthCourseDesc;
import com.hxgy.wechat.service.backstage.IManagerCourseService;
import com.hxgy.wechat.service.backstage.impl.ManagerCourseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CourseMangerController {
    private static final Logger logger = LoggerFactory.getLogger(CourseMangerController.class);
    @Autowired
    private IManagerCourseService iManagerCourseService;

    @RequestMapping("/courselist")//查询所有课程
    @ResponseBody
    public ServerResponse searchCourses(HttpSession session, HttpServletResponse response){
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else
            return iManagerCourseService.findAllCourses();
    }

    @RequestMapping("/courseIndex")
    public String gotoCourseIndex(HttpSession session, HttpServletResponse response){
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else return "courseManager";
    }

    @RequestMapping(value = "/updateCourse", method = RequestMethod.POST)//修改课程
    @ResponseBody
    public ServerResponse updateCourse(@RequestBody HealthCourseDesc healthCourseDesc, HttpSession session, HttpServletResponse response){
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            iManagerCourseService.updateCourse(healthCourseDesc);
            return ServerResponse.createSuccessMessage("修改课程成功");
        }
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)//添加课程
    @ResponseBody
    public ServerResponse addCourse(@RequestBody HealthCourseDesc healthCourseDesc, HttpSession session, HttpServletResponse response){
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return iManagerCourseService.addCourses(healthCourseDesc);
        }
    }

    @RequestMapping(value = "/deleteCourse", method = RequestMethod.POST)//删除课程
    @ResponseBody
    public ServerResponse deleteCourse(@RequestBody String ids, HttpSession session, HttpServletResponse response){
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return iManagerCourseService.deleteCourse(ids);
        }
    }
}
