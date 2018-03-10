package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.Discuss;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 15520 on 2018/3/9.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DiscussRepostoryTest {

    @Autowired
    private DiscussRepostory discussRepostory;

    @Test
    public void testFindOne(){
        Discuss discuss = discussRepostory.findOne(1);
        Assert.assertNotNull(discuss);
    }
    @Test
    public void testFindBySubjectId(){
        List<Discuss> discusses = discussRepostory.findBySubjectId(1);
        Assert.assertNotEquals(0,discusses.size());
    }
}