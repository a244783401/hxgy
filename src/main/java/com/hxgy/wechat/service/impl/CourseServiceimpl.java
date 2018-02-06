package com.hxgy.wechat.service.impl;

import com.hxgy.wechat.VO.CourseDescVo;
import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.base.ResonseCode;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthCategory;
import com.hxgy.wechat.entity.HealthCourseDesc;
import com.hxgy.wechat.entity.UserEnrollCourse;
import com.hxgy.wechat.repostory.HealthCategoryRepostory;
import com.hxgy.wechat.repostory.HealthDescRepostory;
import com.hxgy.wechat.repostory.UserEnrollCourseRepostory;
import com.hxgy.wechat.service.ICourseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zy
 * @create 2018-01-24 10:37
 **/
@Service("iCourseService")
public class CourseServiceimpl implements ICourseService {
    @Autowired
    private HealthCategoryRepostory healthCategoryRepostory;

    @Autowired
    private HealthDescRepostory healthDescRepostory;


    @Autowired
    private UserEnrollCourseRepostory userEnrollCourseRepostory;

    public ServerResponse getCourseDetil(Long courseId){
        HealthCourseDesc healthCourseDesc = healthDescRepostory.findOne(courseId);
        if (healthCourseDesc == null){
            return ServerResponse.createErrorMessage("参数错误！！！");
        }else{

            return ServerResponse.isSuccess(healthCourseDesc);
        }
    }
    private CourseDescVo courseDescToVo(HealthCourseDesc healthCourseDesc){
        CourseDescVo courseDescVo = new CourseDescVo();
        courseDescVo.setCourseName(healthCourseDesc.getCourseName());
        courseDescVo.setCourseCode(healthCourseDesc.getCourseCode());
        courseDescVo.setRecommend(healthCourseDesc.getRecommend());
        courseDescVo.setAuthorName(healthCourseDesc.getAuthorName());
        courseDescVo.setCourseCategoryId(healthCourseDesc.getCourseCategoryId());
        courseDescVo.setCoursePrice(healthCourseDesc.getCoursePrice());
        courseDescVo.setForCrowd(healthCourseDesc.getForCrowd());
        courseDescVo.setEnable(healthCourseDesc.getEnable());
        return courseDescVo;
    }
    public ServerResponse signUp(Long courseId,Long userId){
        HealthCourseDesc healthCourseDesc = healthDescRepostory.findOne(courseId);
        UserEnrollCourse userEnrollCourse = userEnrollCourseRepostory.findByCourseIdAndUserId(courseId,userId);
        if (healthCourseDesc.getRecommend() == 0){
            if (userEnrollCourse != null){
                if (userEnrollCourse.getPay() == Const.BOUGHT){
                    return ServerResponse.createErrorCodeMessage(ResonseCode.BOUGHT.getCode(),"已经购买过该课程!!");
                }
                if (userEnrollCourse.getPay() == Const.NOT_PAY){
                    return ServerResponse.createSuccess(userEnrollCourse,ResonseCode.NEED_PAY.getCode());
                }
            }
            return ServerResponse.createSuccess();
        }
        Integer recommendId = healthCourseDesc.getRecommend();
        userEnrollCourse = userEnrollCourseRepostory.findByCourseIdAndUserId(recommendId.longValue(),userId);
        if (userEnrollCourse == null){
            return ServerResponse.createSuccess();
        }
        if (userEnrollCourse.getPay() == Const.BOUGHT){
            return ServerResponse.createErrorCodeMessage(ResonseCode.BOUGHT.getCode(),"已经购买过该课程!!");
        }
        if (userEnrollCourse.getPay() == Const.NOT_PAY){
            return ServerResponse.createSuccess(userEnrollCourse,ResonseCode.NEED_PAY.getCode());
        }
        return ServerResponse.createSuccess();
    }

    /**
     * 获取所有的分类课程
     * @param periodNum
     * @return
     */
    @Override
    public ServerResponse findAllCourseByVersion(Integer periodNum) {
        List<HealthCategory> healthCategories = healthCategoryRepostory.findByVersion(periodNum);
        if (!CollectionUtils.isEmpty(healthCategories)){
            List<Long> ids = new ArrayList<>();
            for (HealthCategory healthCategory:healthCategories
                 ) {
                ids.add(healthCategory.getId());
            }
            List<HealthCourseDesc> healthCourseDescs = healthDescRepostory.findByCourseCategoryIdIn(ids);
            return ServerResponse.isSuccess(objctToVo(healthCourseDescs));
        }
        return ServerResponse.createError();
    }

    /**
     * HealthCourseDesc 转换为Vo对象
     * @param healthCourseDescs
     * @return
     */
    private List<CourseDescVo> objctToVo(List<HealthCourseDesc> healthCourseDescs){
        List<CourseDescVo> courseDescVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(healthCourseDescs)){
            for (HealthCourseDesc courseDesc:healthCourseDescs
                 ) {
                CourseDescVo courseDescVo = new CourseDescVo();
                courseDescVo.setId(courseDesc.getId());
                courseDescVo.setCourseCategoryId(courseDesc.getCourseCategoryId());
                courseDescVo.setCoursePrice(courseDesc.getCoursePrice());
                courseDescVo.setRecommend(courseDesc.getRecommend());
                courseDescVo.setCourseName(courseDesc.getCourseName());
                courseDescVo.setRecommend(courseDesc.getRecommend());
                courseDescVo.setCourseCode(courseDesc.getCourseCode());
                courseDesc.setEnable(courseDesc.getEnable());
                courseDescVos.add(courseDescVo);
            }
            return courseDescVos;
        }
        return null;
    }
}
