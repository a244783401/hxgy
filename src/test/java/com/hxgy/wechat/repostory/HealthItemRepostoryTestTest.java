package com.hxgy.wechat.repostory;

import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.entity.HealthCourseItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    @Test
    public void testFindByCourseId(){
        List<HealthCourseItem> healthCourseItems = healthItemRepostory.findByCourseId(4L);
        Assert.assertNotEquals(0,healthCourseItems.size());
    }
    @Test
    public void testFindByFree(){
        List<HealthCourseItem> healthCourseItems = healthItemRepostory.findByFree(Const.FREE);
        Assert.assertNotEquals(0,healthCourseItems.size());
    }
}