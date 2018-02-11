package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zy
 * @create 2018-01-23 10:14
 **/
public interface HealthCommentRepostory extends JpaRepository<HealthComment,Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from HealthComment hc where hc.courseitemId in:id")
    int deleteByCourseitemId(@Param("id") List<Long> id);
    @Transactional
    @Modifying
    @Query(value = "delete from HealthComment hc where hc.id in:id")
    int deleteById(@Param("id") List<Long> id);
    List<HealthComment> findAll();
    List<HealthComment> findByCourseitemId(Long courseItemId);
}
