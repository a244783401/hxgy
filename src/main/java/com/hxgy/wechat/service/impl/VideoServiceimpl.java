package com.hxgy.wechat.service.impl;

import com.hxgy.wechat.VO.VideoVO;
import com.hxgy.wechat.base.ResonseCode;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthCourseItem;
import com.hxgy.wechat.entity.UserEnrollCourse;
import com.hxgy.wechat.repostory.HealthHistoryRepostory;
import com.hxgy.wechat.repostory.HealthItemRepostory;
import com.hxgy.wechat.repostory.UserEnrollCourseRepostory;
import com.hxgy.wechat.service.IVideoService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zy
 * @create 2018-01-26 11:36
 **/
@Service("iVideoService")
public class VideoServiceimpl implements IVideoService {

    @Autowired
    private HealthItemRepostory healthItemRepostory;

    @Autowired
    private UserEnrollCourseRepostory userEnrollCourseRepostory;

    @Autowired
    private HealthHistoryRepostory healthHistoryRepostory;

    public ServerResponse findVideoByCourseId(Long courseId){
        List<HealthCourseItem> healthCourseItems = healthItemRepostory.findByCourseId(courseId);
        if (CollectionUtils.isEmpty(healthCourseItems)){
            return ServerResponse.createErrorMessage("当前课程为空");
        }else{
            return ServerResponse.isSuccess(healItemToVo(healthCourseItems));
        }
    }
    public ServerResponse findUserCourseByCourseId(Long courseId,Long videoId){
        List<UserEnrollCourse> userEnrollCourses = userEnrollCourseRepostory.findByCourseId(courseId);
        if (CollectionUtils.isEmpty(userEnrollCourses)){
            return ServerResponse.createErrorCodeMessage(ResonseCode.NEED_BUY.getCode(),"需要购买该课程！！！");
        }
        HealthCourseItem healthCourseItem = healthItemRepostory.findOne(videoId);
        if (healthCourseItem == null){
            return ServerResponse.createErrorCodeMessage(ResonseCode.ILLEGAL_ARGUMENT.getCode(),"参数错误");
        }
        return ServerResponse.isSuccess(healthCourseItem.getUrl());
    }

    public ServerResponse getVideoHistory(Long userId){
        List<Long> videoIdList = healthHistoryRepostory.findVideoIdByUserId(userId);
        if (CollectionUtils.isEmpty(videoIdList)){
            return ServerResponse.createErrorMessage("您尚未有观看记录！！！");
        }else{
            List<HealthCourseItem> healthCourseItems = new ArrayList<>();
            List<VideoVO> videoVOs = new ArrayList<>();
            for (Long videoId:videoIdList
                 ) {
                healthCourseItems.add(healthItemRepostory.findOne(videoId));
            }
            return ServerResponse.isSuccess(healItemToVo(healthCourseItems));
        }
    }


    private List<VideoVO> healItemToVo(List<HealthCourseItem> healthCourseItems){
        List<VideoVO> videoVOs = new ArrayList<>();
        for (HealthCourseItem healthCourseItem:healthCourseItems
             ) {
            VideoVO videoVO = new VideoVO();
            videoVO.setName(healthCourseItem.getName());
            videoVO.setId(healthCourseItem.getId());
            videoVO.setAuthorName(healthCourseItem.getAuthorName());
            videoVO.setCoverurl(healthCourseItem.getCoverUrl());
            videoVO.setCreatedatestr(healthCourseItem.getCreateDate());
            videoVO.setViewNum(healthCourseItem.getViewNum());
            videoVO.setPraiseNum(healthCourseItem.getPraiseNum());
            videoVO.setCourseId(healthCourseItem.getCourseId());
            videoVOs.add(videoVO);
        }
        return videoVOs;
    }
}
