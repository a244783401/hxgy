package com.hxgy.wechat.VO;

import java.util.Date;

/**
 * @author wx
 * @create 2018-02-08 10:12
 **/
public class VideoPlayVo {

    private String url;

    private String coverurl;

    private int viewnum;

    private String createdatestr;

    private String userName;

    private String videodesc;

    private String authorname;

    private Long id;

    private Long courseId;

    private Long userId;

    private String headPortrait;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public int getViewnum() {
        return viewnum;
    }

    public void setViewnum(int viewnum) {
        this.viewnum = viewnum;
    }

    public String getCreatedatestr() {
        return createdatestr;
    }

    public void setCreatedatestr(String createdatestr) {
        this.createdatestr = createdatestr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVideodesc() {
        return videodesc;
    }

    public void setVideodesc(String videodesc) {
        this.videodesc = videodesc;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }
}
