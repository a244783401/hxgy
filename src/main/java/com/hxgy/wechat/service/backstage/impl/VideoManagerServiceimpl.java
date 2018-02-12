package com.hxgy.wechat.service.backstage.impl;

import com.hxgy.wechat.VO.CourseItemVO;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthCourseItem;
import com.hxgy.wechat.entity.HealthHistory;
import com.hxgy.wechat.repostory.HealthCommentRepostory;
import com.hxgy.wechat.repostory.HealthDescRepostory;
import com.hxgy.wechat.repostory.HealthHistoryRepostory;
import com.hxgy.wechat.repostory.HealthItemRepostory;
import com.hxgy.wechat.service.backstage.IVideoManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 10104 on 2018/1/31.
 */
@Service("iVideoManagerService")
public class VideoManagerServiceimpl implements IVideoManagerService {
    @Autowired
    HealthItemRepostory healthItemRepostory;
    @Autowired
    HealthDescRepostory healthDescRepostory;
    @Autowired
    HealthCommentRepostory healthCommentRepostory;
    @Autowired
    private HealthHistoryRepostory healthHistoryRepostory;
    @Override
    public ServerResponse updateVideo(HealthCourseItem healthCourseItem) {
        HealthCourseItem oldCourseItem = healthItemRepostory.findById(healthCourseItem.getId());
        oldCourseItem.setName(healthCourseItem.getName());
        oldCourseItem.setUrl(healthCourseItem.getUrl());
        oldCourseItem.setCoverUrl(healthCourseItem.getCoverUrl());
        oldCourseItem.setViewNum(healthCourseItem.getViewNum());
        oldCourseItem.setEnable(healthCourseItem.getEnable());
        oldCourseItem.setCourseId(healthCourseItem.getCourseId());
        oldCourseItem.setCreateDate(healthCourseItem.getCreateDate());
        oldCourseItem.setAuthorName(healthCourseItem.getAuthorName());
        oldCourseItem.setAuthorDeptName(healthCourseItem.getAuthorDeptName());
        oldCourseItem.setAuthorHospName(healthCourseItem.getAuthorHospName());
        oldCourseItem.setVideoDesc(healthCourseItem.getVideoDesc());
        oldCourseItem.setFree(healthCourseItem.getFree());
        healthItemRepostory.save(oldCourseItem);
        return ServerResponse.createSuccessMessage("修改成功");
    }

    @Override
    public ServerResponse findAllVideos() {
        List<HealthCourseItem> healthCourseItemList = healthItemRepostory.findAll();
        List<CourseItemVO> courseItemVOList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (HealthCourseItem Item : healthCourseItemList) {
            CourseItemVO courseItemVO = new CourseItemVO();
            courseItemVO.setId(Item.getId());
            courseItemVO.setName(Item.getName());
            courseItemVO.setUrl(Item.getUrl());
            courseItemVO.setCoverurl(Item.getCoverUrl());
            courseItemVO.setViewnum(Item.getViewNum() == null ? 0 : Item.getViewNum());
            courseItemVO.setEnable(Item.getEnable()==0?"否":"是");
            courseItemVO.setCourseName(healthDescRepostory.findById(Item.getCourseId()) == null ? null : healthDescRepostory.findById(Item.getCourseId()).getCourseName());
            courseItemVO.setCreateTime(sdf.format(Item.getCreateDate()));
            courseItemVO.setAuthorname(Item.getAuthorName());
            courseItemVO.setAuthordeptname(Item.getAuthorDeptName());
            courseItemVO.setAuthorhospname(Item.getAuthorHospName());
            courseItemVO.setCourseid(Item.getCourseId() == null ? null : Item.getCourseId());
            courseItemVO.setVideoDesc(Item.getVideoDesc());
            courseItemVO.setFree(Item.getFree()==11?"免费":"付费");
            courseItemVOList.add(courseItemVO);
        }
        return ServerResponse.isSuccess(courseItemVOList);
    }

    @Override
    public ServerResponse addVideo(HealthCourseItem healthCourseItem) {
        healthCourseItem.setCreateDate(new Date());
        healthItemRepostory.save(healthCourseItem);
        return ServerResponse.createSuccessMessage("课程添加成功");
    }

    @Override
    public ServerResponse deleteVideo(String ids) {
        ids = ids.substring(8,ids.length()-2);
        List<Long> deleteIds = new ArrayList();
        for(String id :ids.split(",")){
            List<HealthHistory> healthHistories = healthHistoryRepostory.findByVideoId(Long.parseLong(id));
            if(healthHistories.size() > 0){
                return ServerResponse.createErrorMessage("id为"+id+"课程下含有用户观看记录，无法删除");
            }else
            deleteIds.add(Long.parseLong(id));
        }
        healthCommentRepostory.deleteByCourseitemId(deleteIds);
        healthItemRepostory.deleteById(deleteIds);
        return ServerResponse.createSuccessMessage("删除视频成功");
    }
}
