package com.hxgy.wechat.service.backstage;


import com.hxgy.wechat.base.ServerResponse;
import org.springframework.stereotype.Component;

/**
 * Created by 10104 on 2018/1/29.
 */
@Component
public interface ICategService{
    ServerResponse insertCateg(String code, String categoryDesc, Integer version, Integer stand, Integer push);
    ServerResponse upDateCateg(long id, String code, String categoryDesc, Integer version, Integer stand, Integer push);
    ServerResponse deleteCateg(String ids);
    ServerResponse findAllCategories();/*查询所有分类信息*/
}
