package com.hxgy.wechat.VO;

import java.util.Date;

/**
 * Created by 10104 on 2018/2/1.
 */
public class CommentVo {
    private long id;
    private String userName;
    private String commentDesc;
    private float score;
    private String courseName;
    private String courseItemName;
    private String commentDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentDesc() {
        return commentDesc;
    }

    public void setCommentDesc(String commentDesc) {
        this.commentDesc = commentDesc;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseItemName() {
        return courseItemName;
    }

    public void setCourseItemName(String courseItemName) {
        this.courseItemName = courseItemName;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }
}
