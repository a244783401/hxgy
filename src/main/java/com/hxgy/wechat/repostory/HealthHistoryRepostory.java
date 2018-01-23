package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthHistoryRepostory extends JpaRepository<HealthHistory,Long> {
}
