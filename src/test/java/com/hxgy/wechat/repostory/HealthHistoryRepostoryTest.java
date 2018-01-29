package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthHistory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

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
    public void findOne(){
        HealthHistory healthHistory = healthHistoryRepostory.findOne(122L);
        Assert.assertNotNull(healthHistory);
    }
    @Test
    public void findVideoIdByUserId(){
        List<Long> longs = healthHistoryRepostory.findVideoIdByUserId(2L);
        Assert.assertNotEquals(0,longs.size());
    }

}
