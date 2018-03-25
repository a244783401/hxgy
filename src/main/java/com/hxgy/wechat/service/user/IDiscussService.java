package com.hxgy.wechat.service.user;

import com.hxgy.wechat.base.ServerResponse;
import org.springframework.stereotype.Component;

/**
 * Created by wx on 2018/3/9.
 */
@Component
public interface IDiscussService {
    ServerResponse addDiscuss(Integer subjectId,String discussContent,Long userId);
    ServerResponse getDiscussBySubject(Integer subjectId);
    ServerResponse getAllSubject();
    ServerResponse addDiscussSubject(Long userId,String discussItem);
}
