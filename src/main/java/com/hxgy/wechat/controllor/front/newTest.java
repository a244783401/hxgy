package com.hxgy.wechat.controllor.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 10104 on 2018/1/23.
 */
@Controller
@RequestMapping("/test")
public class newTest {
    @RequestMapping("/hello")
    public String Test(){
        return "ManagerLogin";
    }
}
