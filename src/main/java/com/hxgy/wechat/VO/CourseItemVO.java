package com.hxgy.wechat.VO;

/**
 * Created by 10104 on 2018/1/29.
 */
public class CourseItemVO {
    private long id;
    private String name;
    private String url;
    private String coverurl;
    private long viewnum;
    private String enable;
    private String courseName;
    private String createTime;
    private String authorname;
    private String authordeptname;
    private String authorhospname;
    private String videoDesc;
    private long courseid;
    private String free;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public long getViewnum() {
        return viewnum;
    }

    public void setViewnum(long viewnum) {
        this.viewnum = viewnum;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getAuthordeptname() {
        return authordeptname;
    }

    public void setAuthordeptname(String authordeptname) {
        this.authordeptname = authordeptname;
    }

    public String getAuthorhospname() {
        return authorhospname;
    }

    public void setAuthorhospname(String authorhospname) {
        this.authorhospname = authorhospname;
    }

    public long getCourseid() {
        return courseid;
    }

    public void setCourseid(long courseid) {
        this.courseid = courseid;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }
}
