package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.Discuss;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 15520 on 2018/3/9.
 */
public interface DiscussRepostory extends JpaRepository<Discuss,Integer> {
    List<Discuss> findBySubjectId(Integer subjectId);
}
