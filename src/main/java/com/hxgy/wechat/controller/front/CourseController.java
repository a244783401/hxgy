package com.hxgy.wechat.controller.front;

import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ResonseCode;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.UserDetail;
import com.hxgy.wechat.service.ICourseService;
import com.hxgy.wechat.utils.OSSClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * 报名
 *
 * @author zy
 * @create 2018-01-23 14:00
 **/
@Controller
@RequestMapping("/user/course")
public class CourseController {
    @Autowired
    private ICourseService iCourseService;
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);
    /**
     * 点击报名 切换页面
     * @param model
     * @return
     */
    @RequestMapping("/sign_up")
    public String signUp(Model model){
        return "signup";
    }

    /**
     * 获取课程分类
     * @param periodNum
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/sign_list")
    @ResponseBody
    public ServerResponse signList(@RequestParam("periodNum") String periodNum){
        Integer periodNumInt = Integer.parseInt(periodNum);
        if (periodNumInt != null){
            return iCourseService.findAllCourseByVersion(periodNumInt);
        }
        return ServerResponse.createErrorCodeMessage(ResonseCode.ILLEGAL_ARGUMENT.getCode(),ResonseCode.ILLEGAL_ARGUMENT.getMsg());
    }

    /**
     * 点击报名
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/signup_info",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse signupInfo(@RequestParam("id") String id,HttpSession session){
        UserDetail userDetail = (UserDetail) session.getAttribute(Const.CURRENT_USER);
        if (userDetail != null){
            return ServerResponse.createErrorCodeMessage(ResonseCode.NEED_LONGIN.getCode(),"请登陆！！！");
        }
        return ServerResponse.createSuccess();
    }

    @RequestMapping("/signup_info_index")
    public String signInfoIndex(){
        return "signupinfo";
    }

    /**
     * 头像上传
     * @param userDetail
     * @return
     */
    @RequestMapping("/enterForCourse")
    public String enterForCourse(UserDetail userDetail){
        //TO DO
    return null;
    }



    private String loadHeadPortrait(MultipartFile file) throws Exception{
        if (file == null || file.getSize() < 0){
            throw new Exception("头像不能为空!");
        }
        String name = OSSClientUtil.uploadImg2Oss(file);
        String imgUrl = OSSClientUtil.getImgUrl(name);
        return imgUrl;
    }
}
