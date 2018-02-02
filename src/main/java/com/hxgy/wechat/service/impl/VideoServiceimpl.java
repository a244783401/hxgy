package com.hxgy.wechat.service.impl;

import com.hxgy.wechat.VO.HistoryVideoVo;
import com.hxgy.wechat.VO.VideoVO;
import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ResonseCode;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthCourseDesc;
import com.hxgy.wechat.entity.HealthCourseItem;
import com.hxgy.wechat.entity.HealthHistory;
import com.hxgy.wechat.entity.UserEnrollCourse;
import com.hxgy.wechat.repostory.HealthDescRepostory;
import com.hxgy.wechat.repostory.HealthHistoryRepostory;
import com.hxgy.wechat.repostory.HealthItemRepostory;
import com.hxgy.wechat.repostory.UserEnrollCourseRepostory;
import com.hxgy.wechat.service.IVideoService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private HealthDescRepostory healthDescRepostory;

    @Autowired
    private HealthHistoryRepostory healthHistoryRepostory;

    public Map getOrderInfo(Long orderId){
        UserEnrollCourse userEnrollCourse = userEnrollCourseRepostory.findOne(orderId);
        if (userEnrollCourse == null){
            return null;
        }
        HealthCourseDesc healthCourseDesc = healthDescRepostory.findOne(userEnrollCourse.getCourseId());
        Map map = new HashMap<>();
        map.put("price",healthCourseDesc.getCoursePrice());
        map.put("orderNo",userEnrollCourse.getOrderNo());
        map.put("date",userEnrollCourse.getOrderDate());
        return map;
    }

    public ServerResponse findFreeVideo(){
        List<HealthCourseItem> healthCourseItems = healthItemRepostory.findByFree(Const.FREE);
        if (CollectionUtils.isEmpty(healthCourseItems)){
            return ServerResponse.createErrorMessage("没有免费课程！！！");
        }
        return ServerResponse.isSuccess(healItemToVo(healthCourseItems));
    }

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
        List<HealthHistory> healthHistories = healthHistoryRepostory.findVideoIdByUserId(userId);
        if (CollectionUtils.isEmpty(healthHistories)){
            return ServerResponse.createErrorMessage("您尚未有观看记录！！！");
        }else{
            return ServerResponse.isSuccess(getHistoryVo(healthHistories));
        }
    }
    public void updateVideo(Long videoId,Long userId){
        //video的观看记录需要+1，历史记录需要增加一条
        HealthCourseItem healthCourseItem = healthItemRepostory.findOne(videoId);
        healthCourseItem.setViewNum(healthCourseItem.getViewNum()+1);
        healthItemRepostory.save(healthCourseItem);
        HealthHistory history = healthHistoryRepostory.findByVideoId(videoId);
        if (history == null){
            HealthHistory healthHistory = new HealthHistory();
            healthHistory.setCourseId(healthCourseItem.getCourseId());
            healthHistory.setUserId(userId);
            healthHistory.setViewDate(new Date(System.currentTimeMillis()));
            healthHistory.setVideoId(videoId);
            healthHistoryRepostory.save(healthHistory);
        }else {
            history.setViewDate(new Date(System.currentTimeMillis()));
        }
    }
    private List<HistoryVideoVo> getHistoryVo(List<HealthHistory> healthHistories){
            List<HistoryVideoVo> historyVideoVos = new ArrayList<>();
        for (HealthHistory healthHistory:healthHistories
             ) {
            HistoryVideoVo historyVideoVo = new HistoryVideoVo();
            historyVideoVo.setViewDate(healthHistory.getViewDate());
            historyVideoVo.setVedioCurrentTime(healthHistory.getVideoCurrentTime());
            HealthCourseItem healthCourseItem = healthItemRepostory.findOne(healthHistory.getVideoId());
            historyVideoVo.setName(healthCourseItem.getName());
            historyVideoVo.setAuthorName(healthCourseItem.getAuthorName());
            historyVideoVo.setCoverurl(healthCourseItem.getCoverUrl());
            historyVideoVo.setId(healthCourseItem.getId());
            historyVideoVo.setCourseId(healthCourseItem.getCourseId());
            historyVideoVos.add(historyVideoVo);
        }
        return historyVideoVos;
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
