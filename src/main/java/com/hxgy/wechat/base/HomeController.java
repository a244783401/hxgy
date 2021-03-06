package com.hxgy.wechat.base;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author wx
 * @create 2018-01-23 16:54
 **/
@Controller
public class HomeController {
    @GetMapping(value = {"/","/index"})
    public String index(Model model){
        return "psychologyIndex";
    }
    @GetMapping("/404")
    public String notFoundPage() {
        return "404";
    }

    @GetMapping("/403")
    public String accessError() {
        return "403";
    }

    @GetMapping("/500")
    public String internalError() {
        return "500";
    }
    @GetMapping("/logout/page")
    public String logoutPage(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return "psychologyIndex";
    }
    @GetMapping("/visitIndex")
    public String visitIndex(){
        return "visitindex";
    }
    @GetMapping("/myCenterIndex")
    public String index(){
        return "myCenterIndex";
    }
    @GetMapping("/history")
    public String history(){
        return "history";
    }
    @GetMapping("/loginIndex")
    public String loginIndex(){
        return "login";
    }
    @GetMapping("/registerIndex")
    public String registerIndex(){
        return "register";
    }
    @GetMapping("/setting")
    public String setting(){
        return "setting";
    }
    @GetMapping("/modifyInfo")
    public String modifyInfo(){
        return "modifyInfo";
    }
    @GetMapping("/modifyPwd")
    public String modifyPwd(){
        return "modifyPwd";
    }
    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }
    @GetMapping("/centerIntro")
    public String centerIntro(){
        return "centerIntro";
    }
    @GetMapping("/intro_hospital")
    public String introHospital(){
        return "centerIntro/introHospital";
    }
    @GetMapping("/intro_tips")
    public String intro_tips(){
        return "centerIntro/introTips";
    }
    @GetMapping("/intro_student")
    public String intro_student(){
        return "centerIntro/introStudent";
    }
    @GetMapping("/intro_teacher")
    public String intro_teacher(){
        return "centerIntro/introTeacher";
    }
    @GetMapping("/videoPlay")
    public String videoPlay(){
        return "videoPlay";
    }


}
