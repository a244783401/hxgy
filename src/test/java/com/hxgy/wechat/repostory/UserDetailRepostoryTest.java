package com.hxgy.wechat.repostory;


//import com.alibaba.fastjson.JSONObject;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.UserDetail;
import com.hxgy.wechat.utils.MD5Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.plaf.synth.SynthEditorPaneUI;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailRepostoryTest {
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
        userDetail.setEducation(20L);
        userDetail.setEmail("hxgy@xxx.com");
        userDetail.setHeadPortrait("url");
        userDetail.setIdCard("123");
        userDetail.setName("吴谢");
        userDetail.setPhoneno("a1234");
        userDetail.setProfession(10L);
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

    }
}
