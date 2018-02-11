package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthCategory;
import com.hxgy.wechat.entity.HealthCourseDesc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 15520 on 2018/1/23.
 */
public interface HealthDescRepostory extends JpaRepository<HealthCourseDesc,Long> {
    List<HealthCourseDesc> findByCourseCodeIn(List<String> codes);
    List<HealthCourseDesc> findByCourseCategoryIdIn(List<Long> ids);
    List<HealthCourseDesc> findByCategory(Integer category);
    List<HealthCourseDesc> findAll();
    HealthCourseDesc findById(Long id);
    @Transactional
    int deleteByCourseCategoryId(long id);
    List<HealthCourseDesc> findByCourseCategoryId(long id);
    @Transactional
    @Modifying
    @Query(value = "delete from HealthCourseDesc hc where hc.id in:id")
    int deleteById(@Param("id") List<Long> ids);
}
