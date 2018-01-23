package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthCourseItem;
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
public class HealthItemRepostoryTestTest {
    @Autowired
    private HealthItemRepostory healthItemRepostory;

    @Test
    public void testFindOne(){
        HealthCourseItem healthCourseItem = healthItemRepostory.findOne(1L);
        Assert.assertNotNull(healthCourseItem);
    }
}