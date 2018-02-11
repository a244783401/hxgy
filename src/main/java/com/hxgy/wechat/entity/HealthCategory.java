package com.hxgy.wechat.entity;

import javax.persistence.*;

/**
 * @author zy
 * @create 2018-01-23 9:27
 **/
@Entity
@Table(name = "health_course_category")
public class HealthCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(name = "category_desc")
    private String categoryDesc;

    private Integer version;

    private Integer stand;

    private Integer push;

    public Integer getPush() {
        return push;
    }

    public void setPush(Integer push) {
        this.push = push;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getStand() {
        return stand;
    }

    public void setStand(Integer stand) {
        this.stand = stand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }
}
