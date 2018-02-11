package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 15520 on 2018/1/23.
 */
public interface HealthCategoryRepostory extends JpaRepository<HealthCategory,Long> {
    List<HealthCategory> findByVersion(Integer version);
    HealthCategory findByVersionAndStand(Integer version,Integer stand);
    List<HealthCategory> findByPush(Integer push);
    List<HealthCategory> findAll();
    HealthCategory findById(Long id);
    @Transactional
    @Modifying
    @Query(value = "delete from HealthCategory hc where hc.id in:id")
    int deleteById(@Param("id") List<Long> ids);
    @Transactional
    @Modifying
    @Query(value="update HealthCategory hc set hc.push = 0 where hc.version not in:version")
    int updateByVersion(@Param("version")int version);
}
