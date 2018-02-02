package com.hxgy.wechat.repostory;


import com.hxgy.wechat.entity.HealthHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthHistoryRepostory extends JpaRepository<HealthHistory,Long> {
    List<HealthHistory> findVideoIdByUserId(Long userId);
    HealthHistory findByVideoId(Long videoId);
}
