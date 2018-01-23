package com.hxgy.wechat.controllor.front;

import com.hxgy.wechat.base.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 报名
 *
 * @author zy
 * @create 2018-01-23 14:00
 **/
@Controller
@RequestMapping("/user/course")
public class SignUP {
    private static final Logger log = LoggerFactory.getLogger(SignUP.class);
    @RequestMapping("sign_up")
    public String signUp(){
        log.info("sign_up");
        return "signup";
    }

}
