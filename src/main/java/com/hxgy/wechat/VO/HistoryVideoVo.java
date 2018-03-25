package com.hxgy.wechat.VO;

import com.hxgy.wechat.entity.HealthCourseItem;

import java.util.Date;

/**
 * @author wx
 * @create 2018-01-29 11:34
 **/
public class HistoryVideoVo {
    private Long id;

    private Long videoId;

    private String name;

    private String coverurl;

    private String authorName;

    private Date viewDate;

    private Integer vedioCurrentTime;

    private Long courseId;

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

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }

    public Integer getVedioCurrentTime() {
        return vedioCurrentTime;
    }

    public void setVedioCurrentTime(Integer vedioCurrentTime) {
        this.vedioCurrentTime = vedioCurrentTime;
    }
}
