package com.hxgy.wechat.repostory;

import com.hxgy.wechat.base.Const;
import com.hxgy.wechat.entity.Admin;
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
public class AdminRepostoryTest {
    @Autowired
    private AdminRepostory ad;

    @Test
    public void testFindOne(){
        Admin admin = ad.findOne(1L);
        System.out.println(Const.Sex.MALE.getMsg());
        Assert.assertNotNull(admin);
    }
}