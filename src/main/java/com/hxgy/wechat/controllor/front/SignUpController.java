package com.hxgy.wechat.controllor.front;

import com.google.common.base.Strings;
import com.hxgy.wechat.base.ResonseCode;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.service.ICourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Arrays;
import org.thymeleaf.util.ArrayUtils;

import javax.servlet.http.HttpSession;

/**
 * 报名
 *
 * @author zy
 * @create 2018-01-23 14:00
 **/
@Controller
@RequestMapping("/user/course")
public class SignUpController {
    @Autowired
    private ICourseService iCourseService;
    private static final Logger log = LoggerFactory.getLogger(SignUpController.class);
    /**
     * 点击报名 切换页面
     * @param model
     * @return
     */
    @RequestMapping("sign_up")
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
        System.out.print("periodNum"+periodNum);
        if (periodNumInt != null){
            return iCourseService.findAllCourseByVersion(periodNumInt);
        }
        return ServerResponse.createErrorCodeMessage(ResonseCode.ILLEGAL_ARGUMENT.getCode(),ResonseCode.ILLEGAL_ARGUMENT.getMsg());
    }
    @RequestMapping("/signup_info")
    public String signupInfo(@RequestParam("id") String id){
        log.info(id);
        return "signupinfo";
    }


}
