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
    @Column(name = "video_id")
    private Long videoId;
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "view_date")
    private Date viewDate;
    @Column(name = "video_currentTime")
    private Integer videoCurrentTime;
    @Column(name = "enable")
    private Boolean enable;
    @Column(name = "account_code")
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
