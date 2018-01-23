package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthScoreRepostory extends JpaRepository<HealthScore,Long> {
}
