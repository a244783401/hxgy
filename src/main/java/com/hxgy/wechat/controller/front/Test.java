package com.hxgy.wechat.controller.front;

import com.hxgy.wechat.base.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author zy
 * @create 2018-01-18 21:01
 **/
@Controller
@RequestMapping("/index")
public class Test {
    @Autowired

    @RequestMapping("/")
    public String test(){
        System.out.print("start");
        return "index";
    }
    @RequestMapping("/user/log_in")
    @ResponseBody
    public ServerResponse logIn(HttpSession session,String username,String password){
        return ServerResponse.createSuccess();
    }
}
