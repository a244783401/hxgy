package com.hxgy.wechat.repostory;

import com.google.common.collect.Lists;
import com.hxgy.wechat.VO.HistoryVideoVo;
import com.hxgy.wechat.entity.HealthHistory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
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
        List<HealthHistory> longs = healthHistoryRepostory.findVideoIdByUserId(2L);
        Assert.assertNotEquals(0,longs.size());
    }
    @Test
    public void findByVideoId(){
//        HealthHistory healthHistory = healthHistoryRepostory.findByVideoId(88L);
//        Assert.assertNotNull(healthHistory);
    }
    @Test
    public void testFindByTwo(){
        HealthHistory healthHistories = healthHistoryRepostory.findByVideoIdAndUserId(89L,6L);
        Assert.assertNotNull(healthHistories);
    }
    @Test
    public void testDelete(){
        List<Long> longs = Arrays.asList(11L,13L);
        int res = healthHistoryRepostory.deleteVideoByIds(longs,94340L);
        Assert.assertNotEquals(0,res);
    }
}
