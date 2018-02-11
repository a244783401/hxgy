package com.hxgy.wechat.repostory;


import com.hxgy.wechat.entity.HealthHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HealthHistoryRepostory extends JpaRepository<HealthHistory,Long> {
    List<HealthHistory> findVideoIdByUserId(Long userId);
    List<HealthHistory> findByVideoId(long id);
    HealthHistory findByVideoIdAndUserId(Long videoId,Long userId);
    @Transactional
    @Modifying
    @Query(value = "delete from HealthHistory as hh where hh.userId = :userId and hh.videoId in :ids")
    int deleteVideoByIds(@Param("ids") List<Long> ids,@Param("userId") Long userId);
}
