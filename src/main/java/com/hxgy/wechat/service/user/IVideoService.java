package com.hxgy.wechat.service.user;

import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthComment;
import org.springframework.stereotype.Component;

import java.util.List;
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
    ServerResponse deleteHistory(Long userId,List ids);
    ServerResponse getHistory(Long userId,Long videoId);
    ServerResponse getVideoById(Long userId,Long videoId);
    ServerResponse getCommentByVideoId(Long videoId);
    ServerResponse addComment(HealthComment healthComment);
}
