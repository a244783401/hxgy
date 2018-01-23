package com.hxgy.wechat.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 评论
 *
 * @author zy
 * @create 2018-01-23 9:03
 **/
@Entity
@Table(name = "health_comment")
public class HealthComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_desc")
    private String commentDesc;

    @Column(name = "comment_date")
    private Date commentDate;

    private float score;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_head")
    private String userHead;

    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "courseitem_id")
    private Long courseitemId;

    private Integer enable;

    @Column(name = "course_id")
    private Long courseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentDesc() {
        return commentDesc;
    }

    public void setCommentDesc(String commentDesc) {
        this.commentDesc = commentDesc;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getCourseitemId() {
        return courseitemId;
    }

    public void setCourseitemId(Long courseitemId) {
        this.courseitemId = courseitemId;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
