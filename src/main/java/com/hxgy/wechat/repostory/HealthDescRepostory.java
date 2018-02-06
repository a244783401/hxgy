package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthCourseDesc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 15520 on 2018/1/23.
 */
public interface HealthDescRepostory extends JpaRepository<HealthCourseDesc,Long> {
    List<HealthCourseDesc> findByCourseCodeIn(List<String> codes);
    List<HealthCourseDesc> findByCourseCategoryIdIn(List<Long> ids);
    List<HealthCourseDesc> findByRecommend(Integer recommend);
}
