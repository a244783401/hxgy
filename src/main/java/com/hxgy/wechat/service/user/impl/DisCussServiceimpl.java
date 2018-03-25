package com.hxgy.wechat.service.user.impl;

import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.Discuss;
import com.hxgy.wechat.entity.DiscussSubject;
import com.hxgy.wechat.repostory.DiscussRepostory;
import com.hxgy.wechat.repostory.DiscussSubjectRepository;
import com.hxgy.wechat.service.user.IDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wx
 * @create 2018-03-09 10:01
 **/
@Service(value = "iDiscussService")
public class DisCussServiceimpl implements IDiscussService{

    @Autowired
    private DiscussRepostory discussRepostory;

    @Autowired
    private DiscussSubjectRepository discussSubjectRepository;

    public ServerResponse addDiscuss(Integer subjectId,String discussContent,Long userId){
        Discuss discuss = new Discuss();
        discuss.setUserId(userId);
        discuss.setCreateDate(new Date());
        discuss.setDisscussContent(discussContent);
        discuss.setSubjectId(subjectId);
        discussRepostory.save(discuss);
        return ServerResponse.createSuccess();
    }

    public ServerResponse getDiscussBySubject(Integer subjectId){
        return ServerResponse.isSuccess(discussRepostory.findBySubjectId(subjectId));
    }

    public ServerResponse addDiscussSubject(Long userId,String discussItem){

        DiscussSubject discussSubject = new DiscussSubject();
        discussSubject.setCreateDate(new Date());
        discussSubject.setDiscussItem(discussItem);
        discussSubject.setUserId(userId);
        discussSubjectRepository.save(discussSubject);
        return ServerResponse.createSuccess();
    }

    public ServerResponse getAllSubject(){
        return ServerResponse.isSuccess(discussSubjectRepository.findAll());
    }

}
