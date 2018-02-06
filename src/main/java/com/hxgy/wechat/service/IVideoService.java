package com.hxgy.wechat.service;

import com.hxgy.wechat.base.ServerResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 视频
 *
 * @author zy
 * @create 2018-01-26 11:35
 **/
@Component
public interface IVideoService {
    ServerResponse findVideoByCourseId(Long courseId);
    ServerResponse getVideoHistory(Long userId);
    void updateVideo(Long videoId, Long userId);
    ServerResponse findVideoByCourseId(Long corrseId, Long userId, Long videoId);
    ServerResponse findFreeVideo();
    Map getOrderInfo(Long orderId);
}
