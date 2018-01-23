package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 15520 on 2018/1/23.
 */
public interface HealthCategoryRepostory extends JpaRepository<HealthCategory,Long> {
}
