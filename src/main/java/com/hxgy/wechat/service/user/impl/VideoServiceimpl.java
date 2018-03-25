package com.hxgy.wechat.service.user.impl;

import com.hxgy.wechat.VO.HistoryVideoVo;
import com.hxgy.wechat.VO.VideoPlayVo;
import com.hxgy.wechat.VO.VideoVO;
import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ResonseCode;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.*;
import com.hxgy.wechat.repostory.*;
import com.hxgy.wechat.service.user.IVideoService;
import com.hxgy.wechat.utils.DateTimeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wx
 * @create 2018-01-26 11:36
 **/
@Service("iVideoService")
public class VideoServiceimpl implements IVideoService {

    @Autowired
    private HealthCommentRepostory commentRepostory;

    @Autowired
    private HealthItemRepostory healthItemRepostory;

    @Autowired
    private HealthCategoryRepostory healthCategoryRepostory;

    @Autowired
    private UserEnrollCourseRepostory userEnrollCourseRepostory;

    @Autowired
    private UserDetailRepostory userDetailRepostory;

    @Autowired
    private HealthDescRepostory healthDescRepostory;

    @Autowired
    private HealthHistoryRepostory healthHistoryRepostory;


    public ServerResponse addComment(HealthComment healthComment){
        HealthComment healthComment1 = commentRepostory.save(healthComment);
        if (healthComment1 == null){
         return ServerResponse.createErrorMessage("评论失败！！");
        }
        return ServerResponse.createSuccess();
    }

    public ServerResponse getCommentByVideoId(Long videoId){

        List<HealthComment> healthComments = commentRepostory.findByCourseitemId(videoId);
        if (!CollectionUtils.isEmpty(healthComments)){
            return ServerResponse.isSuccess(healthComments);
        }
        return ServerResponse.createSuccess();
    }

    public ServerResponse getVideoById(Long userId,Long videoId){
        UserDetail userDetail = userDetailRepostory.findOne(userId);
        HealthCourseItem healthCourseItem = healthItemRepostory.findOne(videoId);
        VideoPlayVo videoPlayVo = new VideoPlayVo();
        videoPlayVo.setCourseId(healthCourseItem.getCourseId());
        videoPlayVo.setAuthorname(healthCourseItem.getAuthorName());
        videoPlayVo.setHeadPortrait(userDetail.getHeadPortrait());
        videoPlayVo.setCoverurl(healthCourseItem.getCoverUrl());
        videoPlayVo.setCreatedatestr(DateTimeUtil.dateToStr(healthCourseItem.getCreateDate()));
        videoPlayVo.setId(healthCourseItem.getId());
        videoPlayVo.setUrl(healthCourseItem.getUrl());
        videoPlayVo.setUserId(userId);
        videoPlayVo.setVideodesc(healthCourseItem.getVideoDesc());
        videoPlayVo.setViewnum(Integer.parseInt(healthCourseItem.getViewNum().toString()));
        videoPlayVo.setUserName(userDetail.getName());
        return ServerResponse.isSuccess(videoPlayVo);
    }

    public ServerResponse getHistory(Long userId,Long videoId){
        HealthHistory healthHistory = healthHistoryRepostory.findByVideoIdAndUserId(videoId,userId);
        if (healthHistory == null){
            return ServerResponse.createError();
        }
        return ServerResponse.isSuccess(healthHistory);
    }

    public ServerResponse deleteHistory(Long userId,List ids){
        int res = healthHistoryRepostory.deleteVideoByIds(ids,userId);
        if (res > 0){
            return ServerResponse.createSuccess();
        }
        return ServerResponse.createError();
    }

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
            return ServerResponse.createSuccess();
        }else{
            return ServerResponse.isSuccess(healItemToVo(healthCourseItems));
        }
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
        HealthHistory history = healthHistoryRepostory.findByVideoIdAndUserId(videoId,userId);
        if (history == null){
            HealthHistory healthHistory = new HealthHistory();
            healthHistory.setCourseId(healthCourseItem.getCourseId());
            healthHistory.setUserId(userId);
            healthHistory.setViewDate(new Date(System.currentTimeMillis()));
            healthHistory.setVideoId(videoId);
            healthHistoryRepostory.save(healthHistory);
        }else {
            history.setViewDate(new Date(System.currentTimeMillis()));
            healthHistoryRepostory.saveAndFlush(history);
        }
    }

    @Override
    public ServerResponse findVideoByCourseId(Long courseId, Long userId, Long videoId) {
        HealthCourseItem healthCourseItem = healthItemRepostory.findOne(videoId);
        if (healthCourseItem.getFree() == Const.FREE){
            return ServerResponse.isSuccess(healthCourseItem.getUrl());
        }
        UserEnrollCourse userEnrollCourse = userEnrollCourseRepostory.findByCourseIdAndUserId(courseId, userId);
        if (userEnrollCourse != null){
            if (userEnrollCourse.getPay() == Const.NOT_PAY ){
                return ServerResponse.createErrorData(ResonseCode.NEED_PAY.getCode(),userEnrollCourse);
            }
            return ServerResponse.isSuccess(healthCourseItem.getUrl());
        }
        HealthCourseDesc healthCourseDesc = healthDescRepostory.findOne(courseId);
        HealthCategory category = healthCategoryRepostory.findOne(healthCourseDesc.getCourseCategoryId());
        HealthCategory healthCategory = healthCategoryRepostory.findByVersionAndStand(category.getVersion(),Const.TOTAL);
        userEnrollCourse = userEnrollCourseRepostory.findByCourseCategoryIdAndUserId(healthCategory.getId(),userId);
        if (userEnrollCourse != null){
            if (userEnrollCourse.getPay() == Const.NOT_PAY ){
                return ServerResponse.createSuccess(userEnrollCourse,ResonseCode.NEED_PAY.getCode());
            }
            return ServerResponse.isSuccess(healthCourseItem.getUrl());
        }
        return ServerResponse.createErrorCodeMessage(ResonseCode.NEED_BUY.getCode(),ResonseCode.NEED_BUY.getMsg());
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
            historyVideoVo.setVideoId(healthCourseItem.getId());
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
            videoVO.setEnable(healthCourseItem.getEnable());
            videoVO.setCourseId(healthCourseItem.getCourseId());
            videoVOs.add(videoVO);
        }
        return videoVOs;
    }
}
