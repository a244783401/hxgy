package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthCourseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 15520 on 2018/1/23.
 */
public interface HealthItemRepostory extends JpaRepository<HealthCourseItem,Long> {
    List<HealthCourseItem> findAll();
    List<HealthCourseItem> findByCourseId(Long courseId);
    List<HealthCourseItem> findByFree(Integer free);
    HealthCourseItem findById(long id);
    @Transactional
    @Modifying
    @Query(value = "delete from HealthCourseItem hc where hc.id in:id")
    int deleteById(@Param("id")List<Long> id);
}
