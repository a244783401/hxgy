package com.hxgy.wechat.service.user;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author wx
 * @create 2018-01-26 9:07
 **/
@Component
public interface IFileService {
    String upload(MultipartFile file);
}
