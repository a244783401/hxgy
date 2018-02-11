package com.hxgy.wechat.service.backstage;

import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthCourseItem;
import org.springframework.stereotype.Component;

/**
 * Created by 10104 on 2018/1/31.
 */
@Component
public interface IVideoManagerService {
    ServerResponse updateVideo(HealthCourseItem healthCourseItem);
    ServerResponse findAllVideos();/*查询所有视频信息*/
    ServerResponse addVideo(HealthCourseItem healthCourseItem);
    ServerResponse deleteVideo(String ids);
}
