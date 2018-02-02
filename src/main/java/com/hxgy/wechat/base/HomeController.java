package com.hxgy.wechat.base;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author zy
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

}
