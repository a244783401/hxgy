package com.hxgy.wechat.repostory;


import com.hxgy.wechat.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface UserDetailRepostory extends JpaRepository<UserDetail,Long> {

     UserDetail findByNameOrPhoneno(String name,String phoneno);
     UserDetail findByOpenId(String openId);
     UserDetail findByName(String name);
     UserDetail findByPhoneno(String phoneno);
     @Transactional
     @Modifying(clearAutomatically = true)
     @Query(value = "update UserDetail ud set ud.headPortrait = :imageUrl where ud.id = :userId")
     int updateUrlById(@Param("imageUrl") String imageUrl,@Param("userId") Long userId);

}
