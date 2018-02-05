package com.hxgy.wechat.repostory;


//import com.alibaba.fastjson.JSONObject;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.UserDetail;
import com.hxgy.wechat.utils.MD5Util;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.plaf.synth.SynthEditorPaneUI;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailRepostoryTest {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserDetailRepostoryTest.class);
    @Autowired
    UserDetailRepostory userDetailRepostory;
    @Test
    public void getData(){
        UserDetail userDetail=userDetailRepostory.findOne(7L);
//        System.out.println(JSONObject.toJSONString(userDetail));
    }
    @Test
    public void addData(){
        UserDetail userDetail=new UserDetail();
        userDetail.setCop("hxgy");
        userDetail.setEducation("asdas");
        userDetail.setEmail("hxgy@xxx.com");
        userDetail.setHeadPortrait("url");
        userDetail.setIdCard("123");
        userDetail.setName("吴谢");
        userDetail.setPhoneno("a1234");
        userDetail.setProfession("10");
        userDetail.setUserId(7L);
        userDetail.setPassword(MD5Util.MD5EncodeUtf8("123456"));
        userDetailRepostory.save(userDetail);
    }
    @Test
    public void deleteData(){
        userDetailRepostory.delete(8L);
    }
    @Test
    public void get(){
        UserDetail userDetail=userDetailRepostory.findOne(6L);
        logger.info(userDetail.getHeadPortrait());
        userDetailRepostory.updateUrlById("",6L);
        userDetail=userDetailRepostory.findOne(6L);
        logger.info(userDetail.getHeadPortrait());

    }
}
