package com.hxgy.wechat.service.backstage;

import com.hxgy.wechat.base.ServerResponse;
import org.springframework.stereotype.Component;

/**
 * Created by 10104 on 2018/2/1.
 */
@Component
public interface ICommentService {
    ServerResponse deleteComment(String ids);
    ServerResponse queryAllComments();
}
