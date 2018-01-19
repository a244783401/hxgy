package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zy
 * @create 2018-01-18 20:43
 **/
public interface UserRepostory extends JpaRepository<UserTest,Long> {
}
