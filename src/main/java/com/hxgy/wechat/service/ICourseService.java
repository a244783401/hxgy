package com.hxgy.wechat.service;

import com.hxgy.wechat.base.ServerResponse;
import org.springframework.stereotype.Service;

/**
 * 课程Service
 *
 * @author zy
 * @create 2018-01-24 10:35
 **/
public interface ICourseService {

    ServerResponse findAllCourseByVersion(Integer periodNum);
    ServerResponse signUp(Long courseId,Long userId);
    ServerResponse getCourseDetil(Long courseId);
}
