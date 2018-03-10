package com.hxgy.wechat.controller.front;

import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ResonseCode;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.UserDetail;
import com.hxgy.wechat.service.user.IDiscussService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author zy
 * @create 2018-03-09 9:21
 **/
@Controller
public class DiscussController {

    private static final Logger logger = LoggerFactory.getLogger(DiscussController.class);

    @Autowired
    private IDiscussService iDiscussService;

    @RequestMapping("/get_all_subject")
    @ResponseBody
    public ServerResponse getAllSubject(HttpSession session){
        UserDetail userDetail = (UserDetail) session.getAttribute(Const.CURRENT_USER);
        if (userDetail == null){
            return ServerResponse.createErrorCodeMessage(ResonseCode.NEED_LOGIN.getCode(),ResonseCode.NEED_LOGIN.getMsg());
        }
        return iDiscussService.getAllSubject();
    }

    @RequestMapping("/add_subject")
    @ResponseBody
    public ServerResponse addSubject(HttpSession session,@Param("discussSubject") String discussSubject){
        UserDetail userDetail = (UserDetail) session.getAttribute(Const.CURRENT_USER);
        if (userDetail == null){
            return ServerResponse.createErrorCodeMessage(ResonseCode.NEED_LOGIN.getCode(),ResonseCode.NEED_LOGIN.getMsg());
        }
        else return ServerResponse.isSuccess(iDiscussService.addDiscussSubject(userDetail.getId(),discussSubject));
    }

    @RequestMapping("/get_discuss")
    @ResponseBody
    public ServerResponse getDiscuss(@Param("subjectId") String subjectId,HttpSession session){
        UserDetail userDetail = (UserDetail) session.getAttribute(Const.CURRENT_USER);
        if (userDetail == null){
            return ServerResponse.createErrorCodeMessage(ResonseCode.NEED_LOGIN.getCode(),ResonseCode.NEED_LOGIN.getMsg());
        }
        Integer theSubject = null;
        try {
            theSubject = Integer.parseInt(subjectId);
        }catch (Exception e){
            logger.error(e.toString());
            return ServerResponse.createErrorCodeMessage(ResonseCode.ILLEGAL_ARGUMENT.getCode(),"参数错误！！！");
        }
        return iDiscussService.getDiscussBySubject(theSubject);
    }

    @RequestMapping("/add_discuss")
    @ResponseBody
    public ServerResponse addDiscuss(HttpSession session,
                                     @Param("discussContent") String discussContent,
                                     @Param("subjectId") String subjectId){
        UserDetail userDetail = (UserDetail) session.getAttribute(Const.CURRENT_USER);
        if (userDetail == null){
            return ServerResponse.createErrorCodeMessage(ResonseCode.NEED_LOGIN.getCode(),ResonseCode.NEED_LOGIN.getMsg());
        }
        Integer theSubject = null;
        try {
            theSubject = Integer.parseInt(subjectId);
        }catch (Exception e){
            logger.error(e.toString());
            return ServerResponse.createErrorCodeMessage(ResonseCode.ILLEGAL_ARGUMENT.getCode(),"参数错误！！！");
        }
         return iDiscussService.addDiscuss(theSubject,discussContent,userDetail.getId());
    }
}
