package com.hxgy.wechat.service;

import com.hxgy.wechat.base.ServerResponse;

/**
 * 视频
 *
 * @author zy
 * @create 2018-01-26 11:35
 **/
public interface IVideoService {
    ServerResponse findVideoByCourseId(Long courseId);
    ServerResponse findUserCourseByCourseId(Long courseId,Long videoId);
}
