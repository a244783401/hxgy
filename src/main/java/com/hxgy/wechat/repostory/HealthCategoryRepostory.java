package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 15520 on 2018/1/23.
 */
public interface HealthCategoryRepostory extends JpaRepository<HealthCategory,Long> {
    List<HealthCategory> findByVersion(Integer version);
}
