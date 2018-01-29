package com.hxgy.wechat.controllor.front;


import com.hxgy.wechat.VO.LoginInfoVo;
import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.UserDetail;
import com.hxgy.wechat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/login")
public class LoginController {
    @Autowired
    IUserService iUserService;

    @RequestMapping("/myCenterIndex")
    public String myCenterIndex(){
        return "myCenterIndex";
    }

    @RequestMapping("/loginIndex")
    public String loginIndex(){
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/userLogin_psy")
    @ResponseBody
    public ServerResponse userLogin(@RequestParam(value = "phoneno") String phoneno, @RequestParam(value = "password") String password,HttpSession session){
        if(phoneno!=""&&password!="") {
            ServerResponse<UserDetail> serverResponse = iUserService.validateLogin(phoneno,password);
            if (serverResponse.getstatus()==0) {
                session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
            }
            return serverResponse;
        }
       return ServerResponse.createErrorMessage("请输入账号密码!");
    }

    @RequestMapping("/getuserInfo")
    @ResponseBody
    public ServerResponse getuserInfo(HttpSession session){
        if(session!=null) {
            ServerResponse<LoginInfoVo> serverResponse = iUserService.getuserInfo(session);
            if (serverResponse.getstatus() == 0) {
                return serverResponse;
            }
        }
        return ServerResponse.createErrorMessage("请先登陆！");
    }




}
