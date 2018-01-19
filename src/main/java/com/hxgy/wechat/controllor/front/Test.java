package com.hxgy.wechat.controllor.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zy
 * @create 2018-01-18 21:01
 **/
@Controller
public class Test {
    @RequestMapping("/")
    public String test(){
        System.out.print("start");
        return "index";
    }
}
