package com.hxgy.wechat.service.user.impl;

import com.hxgy.wechat.service.user.IFileService;
import com.hxgy.wechat.utils.OSSClientUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wx
 * @create 2018-01-30 15:12
 **/
@Service("iFileService")
public class FileServiceimpl implements IFileService {

    public String upload(MultipartFile file) {
        String imgUrl = null;
        try {
            if (file == null || file.getSize() < 0) {
                throw new Exception("头像不能为空!");
            }
            String name = OSSClientUtil.uploadImg2Oss(file);
            imgUrl = OSSClientUtil.getImgUrl(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return imgUrl;
    }

}
