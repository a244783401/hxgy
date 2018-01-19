package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.UserTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by 15520 on 2018/1/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepostoryTest {
    @Autowired
    private UserRepostory userRepostory;
    @Test
    public void testFindOne(){
        UserTest userTest = userRepostory.findOne(1L);
        System.out.print(userTest.getUserName());
        Assert.assertEquals("张三",userTest.getUserName());
    }
}