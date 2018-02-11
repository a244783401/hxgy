package com.hxgy.wechat.service.backstage;

import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthCourseDesc;
import org.springframework.stereotype.Component;

/**
 * Created by 10104 on 2018/2/7.
 */
@Component
public interface IManagerCourseService{
    ServerResponse updateCourse(HealthCourseDesc healthCourseDesc);
    ServerResponse findAllCourses();/*查询所有课程详细信息*/
    ServerResponse addCourses(HealthCourseDesc healthCourseDesc);
    ServerResponse deleteCourse(String ids);
}
