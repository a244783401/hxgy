package com.hxgy.wechat.repostory;

import com.hxgy.wechat.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepostory extends JpaRepository<UserDetail,Long> {
}
