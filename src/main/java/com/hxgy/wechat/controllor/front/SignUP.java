package com.hxgy.wechat.controllor.front;

import com.hxgy.wechat.base.ResonseCode;
import com.hxgy.wechat.base.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

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
    public String signUp(Model model){
        model.addAttribute("periodNum","44");
        return "signup";
    }
    @RequestMapping(method = RequestMethod.POST,value = "/sign_list")
    @ResponseBody
    public ServerResponse signList(@RequestParam("periodNum") String periodNum, HttpSession session){
        log.info("sign_list start"+periodNum.toString());
        return ServerResponse.createErrorCodeMessage(ResonseCode.ILLEGAL_ARGUMENT.getCode(),"参数错误");
    }
}
