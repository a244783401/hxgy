package com.hxgy.wechat.controller.front;

import com.hxgy.wechat.VO.BowerObject;
import com.hxgy.wechat.VO.UserVo;
import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ResonseCode;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthCourseDesc;
import com.hxgy.wechat.entity.UserDetail;
import com.hxgy.wechat.service.ICourseService;
import com.hxgy.wechat.service.IFileService;
import com.hxgy.wechat.service.IUserService;
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
    @Autowired
    private IFileService iFileService;

    @Autowired
    private IUserService iUserService;
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

    @RequestMapping("/free_course")
    public String freePage(){
        return "freeCourse";
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
        if (userDetail == null){
            return ServerResponse.createErrorCodeMessage(ResonseCode.NEED_LOGIN.getCode(),"请登陆！！！");
        }
        return iCourseService.signUp(Long.parseLong(id),userDetail.getId());
    }

    @RequestMapping("/signup_info_index")
    public String signInfoIndex(@RequestParam("id") String id){
        return "signupinfo";
    }

    @RequestMapping("/getUserDetail")
    @ResponseBody
    public ServerResponse getUserDetail(HttpSession session){
        UserDetail userDetail = (UserDetail)session.getAttribute(Const.CURRENT_USER);
        if (userDetail == null){
            return ServerResponse.createError();
        }
        return ServerResponse.isSuccess(getUserVo(userDetail));
    }


    /**
     * 用户信息输入
     * @param bowerObject
     * @return
     */
    @RequestMapping("/enterForCourse")
    @ResponseBody
    public ServerResponse enterForCourse(BowerObject bowerObject,HttpSession session){
        //TO DO
        UserDetail userDetail = (UserDetail) session.getAttribute(Const.CURRENT_USER);
        if (userDetail == null){
            return ServerResponse.createErrorCodeMessage(ResonseCode.NEED_LOGIN.getCode(),"请登陆");
        }
        return iUserService.addUserDetail(bowerObject,userDetail.getId());
    }


    @RequestMapping("/updateHeadImg")
    @ResponseBody
    public ServerResponse updateHeadImg(@RequestParam(value = "file",required = false) MultipartFile file,HttpSession session) throws Exception {
        UserDetail userDetail = (UserDetail) session.getAttribute(Const.CURRENT_USER);
        if (userDetail == null){
            return ServerResponse.createErrorCodeMessage(ResonseCode.NEED_LOGIN.getCode(),"需要登陆!!");
        }
        if (file != null){
            String imageUrl = iFileService.upload(file);
            iUserService.updateUserImage(imageUrl,userDetail.getId());
            log.info("image"+imageUrl);
            return ServerResponse.createSuccess();
        }
        return ServerResponse.createSuccess();
    }

    @RequestMapping("/course_detil")
    @ResponseBody
    public ServerResponse getCourseDetil(@RequestParam("courseId") String courseId){
        Long courseIdL = Long.parseLong(courseId);
        if (courseIdL == null){
            return ServerResponse.createError();
        }
        return iCourseService.getCourseDetil(courseIdL);
    }

    private UserVo getUserVo(UserDetail userDetail){
        UserVo userVo = new UserVo();
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
}
