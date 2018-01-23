package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthHistory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HealthHistoryRepostoryTest {
    @Autowired
    HealthHistoryRepostory healthHistoryRepostory;
//    @Test
//    public void getData(){
//        HealthHistory healthHistory=healthHistoryRepostory.findOne(127L);
//        System.out.println(JSONObject.toJSONString(healthHistory));
//    }
    @Test
    public void addData(){
        HealthHistory healthHistory=new HealthHistory();
        healthHistory.setAccountCode("sqfa");
        healthHistory.setCourseId(1L);
        healthHistory.setEnable(true);
        healthHistory.setUserID(2L);
        healthHistory.setVideoCurrentTime(20);
        healthHistory.setVideoId(3L);
        healthHistory.setViewDate(new Date());
        healthHistoryRepostory.save(healthHistory);
    }
    @Test
    public void deleteData(){
        healthHistoryRepostory.delete(140L);
    }

}
