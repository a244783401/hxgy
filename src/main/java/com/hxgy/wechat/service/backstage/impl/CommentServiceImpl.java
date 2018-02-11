package com.hxgy.wechat.service.backstage.impl;

import com.hxgy.wechat.VO.CommentVo;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthComment;
import com.hxgy.wechat.entity.HealthCourseItem;
import com.hxgy.wechat.repostory.HealthCommentRepostory;
import com.hxgy.wechat.repostory.HealthDescRepostory;
import com.hxgy.wechat.repostory.HealthItemRepostory;
import com.hxgy.wechat.service.backstage.ICommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10104 on 2018/2/1.
 */
@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    HealthCommentRepostory healthCommentRepostory;
    @Autowired
    HealthDescRepostory healthDescRepostory;
    @Autowired
    HealthItemRepostory healthItemRepostory;
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Override
    public ServerResponse deleteComment(String ids) {
        ids = ids.substring(8, ids.length() - 2);
        List<Long> deletIds = new ArrayList();
        int status = 0;
        for (String id : ids.split(",")) {
            deletIds.add(Long.parseLong(id));
        }
        status = healthCommentRepostory.deleteById(deletIds);
        if (status == 0) {
            return ServerResponse.createErrorMessage("删除评论失败");
        }
        return ServerResponse.createSuccessMessage("删除评论成功");
    }

    @Override
    public ServerResponse queryAllComments() {
        List<HealthComment> healthCommentList = healthCommentRepostory.findAll();
        List<CommentVo> commentVoList = new ArrayList<>();
        for (HealthComment healthComment : healthCommentList) {
            CommentVo commentVo = new CommentVo();
            commentVo.setId(healthComment.getId());
            commentVo.setUserName(healthComment.getUserName());
            commentVo.setCommentDesc(healthComment.getCommentDesc());
            commentVo.setScore(healthComment.getScore());
            commentVo.setCourseName(healthComment.getUserName());
            commentVo.setCommentDate(healthComment.getCommentDate().toString());
            commentVo.setCourseItemName(healthItemRepostory.findById(healthComment.getCourseitemId()).getName());
            commentVo.setCourseName(healthDescRepostory.findById(healthComment.getCourseId()).getCourseDesc());
            commentVoList.add(commentVo);
        }
        return ServerResponse.isSuccess(commentVoList);
    }
}
