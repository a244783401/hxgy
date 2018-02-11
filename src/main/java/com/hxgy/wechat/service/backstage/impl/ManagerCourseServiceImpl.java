package com.hxgy.wechat.service.backstage.impl;

import com.hxgy.wechat.VO.CourseVo;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthCategory;
import com.hxgy.wechat.entity.HealthCourseDesc;
import com.hxgy.wechat.entity.HealthCourseItem;
import com.hxgy.wechat.entity.HealthHistory;
import com.hxgy.wechat.repostory.*;
import com.hxgy.wechat.service.backstage.IManagerCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 10104 on 2018/2/7.
 */
@Service("iManagerCourseService")
public class ManagerCourseServiceImpl implements IManagerCourseService {
    @Autowired
    private HealthCategoryRepostory healthCategoryRepostory;

    @Autowired
    private HealthDescRepostory healthDescRepostory;

    @Autowired
    private HealthItemRepostory healthItemRepostory;

    @Override
    public ServerResponse updateCourse(HealthCourseDesc healthCourseDesc) {
        HealthCourseDesc oldhealthCourseDesc = healthDescRepostory.findById(healthCourseDesc.getId());
        oldhealthCourseDesc.setCourseName(healthCourseDesc.getCourseName());
        oldhealthCourseDesc.setCourseDesc(healthCourseDesc.getCourseDesc());
        oldhealthCourseDesc.setCoursePrice(healthCourseDesc.getCoursePrice());
        oldhealthCourseDesc.setForCrowd(healthCourseDesc.getForCrowd());
        oldhealthCourseDesc.setRecommend(healthCourseDesc.getRecommend());
        oldhealthCourseDesc.setEnable(healthCourseDesc.getEnable());
        oldhealthCourseDesc.setCourseCategoryId(healthCourseDesc.getCourseCategoryId());
        oldhealthCourseDesc.setAuthorDeptName(healthCourseDesc.getAuthorDeptName());
        oldhealthCourseDesc.setAuthorDeptName(healthCourseDesc.getAuthorDeptName());
        oldhealthCourseDesc.setAuthorHospName(healthCourseDesc.getAuthorHospName());
        oldhealthCourseDesc.setCourseCode(healthCourseDesc.getCourseCode());
        oldhealthCourseDesc.setCourseCoverUrl(healthCourseDesc.getCourseCoverUrl());
        oldhealthCourseDesc.setCourseCoverUrl(healthCourseDesc.getCourseCoverUrl());
        oldhealthCourseDesc.setCourseCode(healthCourseDesc.getCourseCode());
        healthDescRepostory.save(oldhealthCourseDesc);
        return ServerResponse.createSuccessMessage("数据修改成功");
    }

    @Override
    public ServerResponse findAllCourses() {
        List<HealthCourseDesc> list = healthDescRepostory.findAll();
        List<CourseVo> courseVoList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (HealthCourseDesc Li : list) {
            CourseVo courseVo = new CourseVo();
            HealthCategory healthCategory = new HealthCategory();
            healthCategory = healthCategoryRepostory.findById(Li.getCourseCategoryId());
            courseVo.setId(Li.getId());
            courseVo.setCoursename(Li.getCourseName());
            courseVo.setCoursedesc(Li.getCourseDesc());
            courseVo.setCourseprice(Li.getCoursePrice());
            courseVo.setForcrowd(Li.getForCrowd());
            courseVo.setLearnnum(Li.getLearnNum());
            courseVo.setIsrecommend(Li.getRecommend() == 0 ? "否" : "是");
            courseVo.setScore(Li.getScore());
            courseVo.setEnable(Li.getEnable() == 0 ? "否" : "是");
            courseVo.setCateg(healthCategory == null ? "暂无分类信息" : healthCategory.getCategoryDesc());
            courseVo.setCreateTime(sdf.format(Li.getCreateDate()));
            courseVo.setAuthorname(Li.getAuthorName());
            courseVo.setAuthordeptname(Li.getAuthorDeptName());
            courseVo.setAuthorhospname(Li.getAuthorHospName());
            courseVo.setCoursecategid(healthCategory == null ? null : healthCategory.getId());
            courseVo.setCourseCode(Li.getCourseCode());
            courseVo.setCourseCoverUrl(Li.getCourseCoverUrl());
            courseVoList.add(courseVo);
        }
        return ServerResponse.isSuccess(courseVoList);
    }

    @Override
    public ServerResponse addCourses(HealthCourseDesc healthCourseDesc) {
        healthCourseDesc.setCreateDate(new Date());
        healthDescRepostory.save(healthCourseDesc);
        return ServerResponse.createSuccessMessage("添加课程成功");
    }

    @Override
    public ServerResponse deleteCourse(String ids) {
        ids = ids.substring(8, ids.length() - 2);
        List<Long> deletIds = new ArrayList();
        int status = 0;
        for (String id : ids.split(",")) {
            List<HealthCourseItem> healthCourseItems = healthItemRepostory.findByCourseId(Long.parseLong(id));
            if (healthCourseItems.size() > 0) {
                return ServerResponse.createErrorMessage("课程id为"+id+"下含有视频，无法删除");
            }else
                deletIds.add(Long.parseLong(id));
        }
        status = healthDescRepostory.deleteById(deletIds);
        if(status==0){
            return ServerResponse.createError();
        }
        return ServerResponse.createSuccessMessage("删除课程成功");
    }
}
