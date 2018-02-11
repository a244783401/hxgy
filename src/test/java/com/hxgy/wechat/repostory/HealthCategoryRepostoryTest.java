package com.hxgy.wechat.repostory;

import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.entity.HealthCategory;
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
public class HealthCategoryRepostoryTest {
    @Autowired
    private HealthCategoryRepostory healthCategoryRepostory;
    @Test
    public void testFindOne(){
        HealthCategory healthCategory = healthCategoryRepostory.findOne(1L);
        Assert.assertNotNull(healthCategory);
    }
    @Test
    public void testFindList(){
        List<HealthCategory> healthCategories = healthCategoryRepostory.findByVersion(44);
        Assert.assertNotEquals(0,healthCategories.size());
    }
    @Test
    public void testFindByStandAndVersion(){
        HealthCategory healthCategory = healthCategoryRepostory.findByVersionAndStand(44, Const.TOTAL);
        Assert.assertNotNull(healthCategory);
    }
    @Test
    public void testFindByPush(){
        List<HealthCategory> healthCategories = healthCategoryRepostory.findByPush(Const.PUSH);
        Assert.assertNotEquals(0,healthCategories.size());
    }
}