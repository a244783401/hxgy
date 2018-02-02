package com.hxgy.wechat.controller.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信授权登陆测试类
 *
 * @author zy
 * @create 2018-01-20 14:43
 **/
@RestController
@RequestMapping("/test")
public class WechatLoginTest {
    private static final Logger logger = LoggerFactory.getLogger(WechatLoginTest.class);
    @RequestMapping("/login")
    public void test(@RequestParam("code") String code){
        logger.info("asdasdasdsadadas");
        logger.info("code{}",code);
    }

}
