package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.DiscussSubject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by 15520 on 2018/3/9.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DiscussSubjectRepositoryTest {

    @Autowired
    private DiscussSubjectRepository discussSubjectRepository;

    @Test
    public void testFindOne(){
        DiscussSubject discussSubject = discussSubjectRepository.findOne(1);
        Assert.assertNotNull(discussSubject);
    }
}