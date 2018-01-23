package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthCourseDesc;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by 15520 on 2018/1/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HealthDescRepostoryTest {
    @Autowired
    private HealthDescRepostory heal;

    @Test
    public void testFindOne(){
        HealthCourseDesc healthCourseDesc = heal.findOne(1L);
        Assert.assertNotNull(healthCourseDesc);
    }
}