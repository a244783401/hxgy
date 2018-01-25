package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthCourseDesc;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

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
    @Test
    public void testFindBycode(){
        List<Long> codes = Arrays.asList(2L,3L);
        List<HealthCourseDesc> healthCourseDescs = heal.findByCourseCategoryIdIn(codes);
        Assert.assertNotEquals(0,healthCourseDescs.size());
    }
}