package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthCourseItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 15520 on 2018/1/23.
 */
public interface HealthItemRepostory extends JpaRepository<HealthCourseItem,Long> {
    List<HealthCourseItem> findByCourseId(Long courseId);
}
