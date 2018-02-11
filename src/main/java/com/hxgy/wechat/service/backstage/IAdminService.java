package com.hxgy.wechat.service.backstage;

import com.hxgy.wechat.base.ServerResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by 10104 on 2018/1/25.
 */
@Component
public interface IAdminService {
    ServerResponse finduser(String username, String password, HttpSession session);
}
