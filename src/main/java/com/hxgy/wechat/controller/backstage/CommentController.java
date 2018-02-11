package com.hxgy.wechat.controller.backstage;

import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.service.backstage.ICommentService;
import com.hxgy.wechat.service.backstage.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 10104 on 2018/2/1.
 */
@Controller
@RequestMapping("/admin")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;
    @RequestMapping("/commentIndex")//跳转分类
    public String gotoCommenIndex(HttpSession session, HttpServletResponse response){
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else return "commentManager";
    }
    @RequestMapping(value="deleteComment" , method = RequestMethod.POST)//删除分类
    @ResponseBody
    public ServerResponse deleteComment(@RequestBody String ids,HttpSession session, HttpServletResponse response){
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else{
            return iCommentService.deleteComment(ids);
        }
    }
    @RequestMapping("/commentList")//查询所有评论
    @ResponseBody
    public ServerResponse queryComments(HttpSession session, HttpServletResponse response){
        if (session.getAttribute(Const.CURRENT_ADMIN) == null) {
            try {
                response.sendRedirect("check");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else
        return iCommentService.queryAllComments();
    }
}
