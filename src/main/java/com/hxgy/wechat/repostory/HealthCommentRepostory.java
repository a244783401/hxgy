package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.HealthComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zy
 * @create 2018-01-23 10:14
 **/
public interface HealthCommentRepostory extends JpaRepository<HealthComment,Long> {
}
