package com.hxgy.wechat.controller.front;

import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ResonseCode;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.UserDetail;
import com.hxgy.wechat.service.ICourseService;
import com.hxgy.wechat.service.IVideoService;
import com.hxgy.wechat.utils.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author zy
 * @create 2018-01-26 11:06
 **/
@Controller
@RequestMapping("/video")
public class VideoController {

    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private ICourseService iCourseService;

    @Autowired
    private IVideoService iVideoService;

    @RequestMapping("/allcourse")
    public String allCourse(){
        return "visitindex";
    }

    /**
     * 获取课程分类
     * @param periodNum
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/allcourse_info")
    @ResponseBody
    public ServerResponse allCourse(@RequestParam("periodNum") String periodNum){
        Integer periodNumInt = Integer.parseInt(periodNum);
        if (periodNumInt != null){
            return iCourseService.findAllCourseByVersion(periodNumInt);
        }
        return ServerResponse.createErrorCodeMessage(ResonseCode.ILLEGAL_ARGUMENT.getCode(),ResonseCode.ILLEGAL_ARGUMENT.getMsg());
    }

    @RequestMapping(method = RequestMethod.POST,value = "/find_all_video")
    @ResponseBody
    public ServerResponse findAllVideoByCourseId(@RequestParam(value = "courseId",defaultValue = "4") String courseId){
         Long courseIdL = Long.parseLong(courseId);
        if (courseIdL != null){
            return iVideoService.findVideoByCourseId(courseIdL);
        }
        return ServerResponse.createErrorCodeMessage(ResonseCode.ILLEGAL_ARGUMENT.getCode(),ResonseCode.ILLEGAL_ARGUMENT.getMsg());
    }

    @RequestMapping(value = "/video_play",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse videoPlay(@RequestParam("videoId") String videoId,@RequestParam("courseId") String courseId,HttpSession session){
        Long videoIdL = Long.parseLong(videoId);
        Long courseIdL = Long.parseLong(courseId);
        if (courseIdL == null || videoIdL == null){
            return ServerResponse.createErrorCodeMessage(ResonseCode.ILLEGAL_ARGUMENT.getCode(),ResonseCode.ILLEGAL_ARGUMENT.getMsg());
        }
        UserDetail userDetail = (UserDetail)session.getAttribute(Const.CURRENT_USER);
        if (userDetail == null){
            return ServerResponse.createErrorCodeMessage(ResonseCode.NEED_LOGIN.getCode(),"请登陆！！！");
        }
        ServerResponse serverResponse = iVideoService.findVideoByCourseId(courseIdL, userDetail.getId(), videoIdL);
        if (serverResponse.getstatus() == 0){
            iVideoService.updateVideo(videoIdL,userDetail.getId());
        }
        return serverResponse;

    }

    @RequestMapping("/history")
    @ResponseBody
    public ServerResponse history(HttpSession session){
        UserDetail userDetail = (UserDetail) session.getAttribute(Const.CURRENT_USER);
        if (userDetail == null){
            return ServerResponse.createErrorCodeMessage(ResonseCode.NEED_LOGIN.getCode(),"请登陆！！！");
        }
        Long userId = userDetail.getId();
        return  iVideoService.getVideoHistory(userId);
    }
    @RequestMapping("/get_freeCourse")
    @ResponseBody
    public ServerResponse getFreeCourse(){
        return iVideoService.findFreeVideo();
    }
    @RequestMapping("/order")
    public String order(@RequestParam("orderId") String id, HttpSession session, Model model){
        UserDetail userDetail = (UserDetail) session.getAttribute(Const.CURRENT_USER);
        if (userDetail != null){
            Long orderId = Long.parseLong(id);
            Map map = iVideoService.getOrderInfo(orderId);
            model.addAttribute("price",map.get("price"));
            model.addAttribute("date", DateTimeUtil.dateToStr((Date) map.get("date")));
            model.addAttribute("orderNum",map.get("orderNo"));
            return "order";
        }
        return "psychologyIndex";
    }
}
