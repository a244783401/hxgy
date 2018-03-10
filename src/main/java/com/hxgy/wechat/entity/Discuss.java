package com.hxgy.wechat.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zy
 * @create 2018-03-09 9:23
 **/
@Entity
@Table(name = "discuss")
public class Discuss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "discuss_content")
    private String disscussContent;

    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "create_date")
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDisscussContent() {
        return disscussContent;
    }

    public void setDisscussContent(String disscussContent) {
        this.disscussContent = disscussContent;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
