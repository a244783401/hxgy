package com.hxgy.wechat.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "health_history")
public class HealthHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "videoId")
    private Long videoId;
    @Column(name = "courseId")
    private Long courseId;
    @Column(name = "userID")
    private Long userID;
    @Column(name = "viewDate")
    private Date viewDate;
    @Column(name = "videoCurrentTime")
    private Integer videoCurrentTime;
    @Column(name = "enable")
    private Boolean enable;
    @Column(name = "accountCode")
    private String accountCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }

    public Integer getVideoCurrentTime() {
        return videoCurrentTime;
    }

    public void setVideoCurrentTime(Integer videoCurrentTime) {
        this.videoCurrentTime = videoCurrentTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
}
