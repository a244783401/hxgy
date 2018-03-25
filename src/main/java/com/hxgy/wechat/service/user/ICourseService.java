package com.hxgy.wechat.service.user;

import com.hxgy.wechat.base.ServerResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 课程Service
 *
 * @author wx
 * @create 2018-01-24 10:35
 **/
public interface ICourseService {
    Map findCurrentCourse();
    ServerResponse findAllCourseByVersion(Integer periodNum);
    ServerResponse signUp(Long courseId,Long userId);
    ServerResponse getCourseDetil(Long courseId);
    ServerResponse getAllInineCourse();
}
