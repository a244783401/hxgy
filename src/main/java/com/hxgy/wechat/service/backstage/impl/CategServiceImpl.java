package com.hxgy.wechat.service.backstage.impl;

import com.hxgy.wechat.VO.CategoryVo;
import com.hxgy.wechat.base.ServerResponse;
import com.hxgy.wechat.entity.HealthCategory;
import com.hxgy.wechat.entity.HealthCourseDesc;
import com.hxgy.wechat.repostory.HealthCategoryRepostory;
import com.hxgy.wechat.repostory.HealthDescRepostory;
import com.hxgy.wechat.repostory.HealthItemRepostory;
import com.hxgy.wechat.repostory.HealthScoreRepostory;
import com.hxgy.wechat.service.backstage.ICategService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by 10104 on 2018/1/29.
 */
@Service("iCategService")
public class CategServiceImpl implements ICategService {
    @Autowired
    HealthCategoryRepostory healthCategoryRepostory;
    @Autowired
    HealthDescRepostory healthDescRepostory;
    @Autowired
    HealthItemRepostory healthItemRepostory;
    @Autowired
    HealthScoreRepostory healthScoreRepostory;
    @Autowired

    private static final Logger logger = LoggerFactory.getLogger(CategServiceImpl.class);

    @Override
    public ServerResponse findAllCategories() {
        ListIterator<HealthCategory> Li = healthCategoryRepostory.findAll().listIterator();
        List<CategoryVo> categoryVoList = new ArrayList<>();
        while (Li.hasNext()) {/*提取需要的信息*/
            HealthCategory healthCategory = Li.next();
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setId(healthCategory.getId());
            categoryVo.setCode(healthCategory.getCode());
            categoryVo.setcategdesc(healthCategory.getCategoryDesc());
            categoryVo.setStand(healthCategory.getStand()==0?"是":"否");
            categoryVo.setVersion(healthCategory.getVersion()==null?null:healthCategory.getVersion()+"期");
            categoryVo.setPush(healthCategory.getPush()==0?"否":"是");
            categoryVoList.add(categoryVo);
        }
        return ServerResponse.isSuccess(categoryVoList);/*返回信息*/
    }

    @Override
    public ServerResponse insertCateg(String code, String categoryDesc, Integer version, Integer stand,Integer push) {
        HealthCategory healthCategory = new HealthCategory();
        healthCategory.setCode(code);
        healthCategory.setCategoryDesc(categoryDesc);
        healthCategory.setStand(stand == null ? null : stand);
        healthCategory.setVersion(version == null ? null : version);
        if(stand==1&&healthCategoryRepostory.findByVersionAndStand(version,stand)!=null){
           return ServerResponse.createErrorMessage("已经存在"+version+"期全套课程分类");
        }
        healthCategoryRepostory.updateByVersion(version,push);
        if(push==1){
            healthCategoryRepostory.updateByVersion(version);
        }
        healthCategory.setPush(push);
        healthCategoryRepostory.save(healthCategory);
        return ServerResponse.createSuccessMessage("添加成功");
    }

    @Override
    public ServerResponse upDateCateg(long id, String code, String categoryDesc, Integer version, Integer stand ,Integer push) {
        HealthCategory healthCategory = healthCategoryRepostory.findById(id);
        healthCategory.setCode(code);
        healthCategory.setCategoryDesc(categoryDesc);
        healthCategory.setStand(stand == null ? null : stand);
        healthCategory.setVersion(version == null ? null : version);
        if(stand==1&&healthCategoryRepostory.findByVersionAndStand(version,stand)!=null){
            return ServerResponse.createErrorMessage("已经存在"+version+"期全套课程分类");
        }
        healthCategoryRepostory.updateByVersion(version,push);
        if(push==1){
            healthCategoryRepostory.updateByVersion(version);
        }
        healthCategory.setPush(push);
        healthCategoryRepostory.save(healthCategory);
        return ServerResponse.createSuccessMessage("修改成功");
    }

    @Override
    public ServerResponse deleteCateg(String ids) {
        ids = ids.substring(8, ids.length() - 2);
        List<Long> deletIds = new ArrayList();
        int status = 0;
        for (String id : ids.split(",")) {
            List<HealthCourseDesc> healthCourseDesc = healthDescRepostory.findByCourseCategoryId(Long.parseLong(id));
            if (healthCourseDesc.size() > 0) {
                return ServerResponse.createErrorMessage("分类id为"+id+"下含有课程，无法删除");
            } else
                deletIds.add(Long.parseLong(id));
        }
        status = healthCategoryRepostory.deleteById(deletIds);
        if(status==0){
            return ServerResponse.createErrorMessage("删除分类失败");
        }
        return ServerResponse.createSuccessMessage("删除分类成功");
    }
}
