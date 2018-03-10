package com.hxgy.wechat.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zy
 * @create 2018-03-09 9:21
 **/
@Entity
@Table(name = "discuss_subject")
public class DiscussSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "discuss_item")
    private String discussItem;

    @Column(name = "user_id")
    private Long userId;


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

    public String getDiscussItem() {
        return discussItem;
    }

    public void setDiscussItem(String discussItem) {
        this.discussItem = discussItem;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
