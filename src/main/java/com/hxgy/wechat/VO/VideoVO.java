package com.hxgy.wechat.VO;

import java.util.Date;

/**
 * @author zy
 * @create 2018-01-26 11:39
 **/
public class VideoVO {

    private String coverurl;

    private Long id;

    private String name;

    private String authorName;

    private Long viewNum;

    private Long praiseNum;

    private Date createdatestr;

    private Long courseId;

    private String videoTime;

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long getViewNum() {
        return viewNum;
    }

    public void setViewNum(Long viewNum) {
        this.viewNum = viewNum;
    }

    public Long getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Long praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Date getCreatedatestr() {
        return createdatestr;
    }

    public void setCreatedatestr(Date createdatestr) {
        this.createdatestr = createdatestr;
    }
}
