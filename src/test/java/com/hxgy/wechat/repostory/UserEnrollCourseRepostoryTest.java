package com.hxgy.wechat.repostory;


//import com.alibaba.fastjson.JSONObject;
import com.hxgy.wechat.entity.UserEnrollCourse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEnrollCourseRepostoryTest {
    @Autowired
    private UserEnrollCourseRepostory userEnrollCourseRepostory;
    @Test
    public void getData(){
        UserEnrollCourse userEnrollCourse=userEnrollCourseRepostory.findOne(20L);
        Assert.assertNotNull(userEnrollCourse);
//        System.out.println(JSONObject.toJSONString(userEnrollCourse));
    }
//    @Test
//    public void addData(){
//        UserEnrollCourse userEnrollCourse=new UserEnrollCourse();
//
//        userEnrollCourse.setCourseId(2L);
//        userEnrollCourse.setLearnPeriod(5L);
//        userEnrollCourse.setTotalPeriod(10L);
//        userEnrollCourse.setUserId(22222L);
//        userEnrollCourseRepostory.save(userEnrollCourse);
//    }
//    @Test
//    public void deleteData(){
//        userEnrollCourseRepostory.delete(58L);
//    }
//    @Test
//    public void findByCourseId(){
//        List<UserEnrollCourse> userEnrollCourses= userEnrollCourseRepostory.findByCourseId(2L);
//        Assert.assertNotEquals(0,userEnrollCourses.size());
//    }
//
//    @Test
//    public void findByUserId(){
//           UserEnrollCourse userEnrollCourse = userEnrollCourseRepostory.findByCourseIdAndUserId(8L,6L);
//            Assert.assertNotNull(userEnrollCourse);
//    }

//    @Test
//    public void deleteData(){
//        userEnrollCourseRepostory.delete(58L);
//    }
//    @Test
//    public void findByCourseId(){
//        List<UserEnrollCourse> userEnrollCourses= userEnrollCourseRepostory.findByCourseId(2L);
//        Assert.assertNotEquals(0,userEnrollCourses.size());
//    }
//
//    @Test
//    public void findByUserId(){
////        UserEnrollCourse userEnrollCourse = userEnrollCourseRepostory.findByUserId(6L);
////        Assert.assertNotNull(userEnrollCourse);
//    }
    @Test
    public void test(){
        UserEnrollCourse userEnrollCourse = userEnrollCourseRepostory.findByCourseCategoryIdAndUserId(3L,94338L);
        Assert.assertNotNull(userEnrollCourse);
    }
}
