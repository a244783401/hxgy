package com.hxgy.wechat.repostory;


import com.hxgy.wechat.entity.UserDetail;
import com.hxgy.wechat.entity.UserEnrollCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEnrollCourseRepostory extends JpaRepository<UserEnrollCourse,Long> {
        List<UserEnrollCourse> findByCourseId(Long courseId);
        UserEnrollCourse findByUserId(Long userId);
        UserEnrollCourse findByCourseIdAndUserId(Long courseId,Long userId);
        UserEnrollCourse findByCourseCategoryIdAndUserId(Long courseCategoryId,Long userId);
}
