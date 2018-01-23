package com.hxgy.wechat.repostory;

import com.alibaba.fastjson.JSONObject;
import com.hxgy.wechat.entity.HealthScore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HealthScoreRepostoryTest {
    @Autowired
    HealthScoreRepostory healthScoreRepostory;
    @Test
    public void getData(){
        HealthScore healthScore=healthScoreRepostory.findOne(2L);
        System.out.println(JSONObject.toJSONString(healthScore));
    }
    @Test
    public void addData(){
        HealthScore healthScore=new HealthScore();
        healthScore.setScore(20);
        healthScore.setCourseId(1L);
        healthScore.setScoreDate(new Date());
        healthScore.setUserId(2L);
        healthScore.setUserName("吴谢");
        healthScoreRepostory.save(healthScore);
    }
    @Test
    public void deleteData(){
        healthScoreRepostory.delete(4L);
    }
}
