package com.hxgy.wechat.VO;

import java.util.Date;

/**
 * Created by 10104 on 2018/1/26.
 */
public class CourseVo {
    private Long Id;
    private String coursename;
    private String coursedesc;
    private String courseprice;
    private String forcrowd;
    private Long learnnum;
    private String isrecommend;
    private float score;
    private String enable;
    private String categ;
    private String createTime;
    private String authorname;
    private String authordeptname;
    private String authorhospname;
    private String courseCoverUrl;
    private Long coursecategid;
    private String courseCode;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursedesc() {
        return coursedesc;
    }

    public void setCoursedesc(String coursedesc) {
        this.coursedesc = coursedesc;
    }

    public String getCourseprice() {
        return courseprice;
    }

    public void setCourseprice(String courseprice) {
        this.courseprice = courseprice;
    }

    public String getForcrowd() {
        return forcrowd;
    }

    public void setForcrowd(String forcrowd) {
        this.forcrowd = forcrowd;
    }

    public Long getLearnnum() {
        return learnnum;
    }

    public void setLearnnum(Long learnnum) {
        this.learnnum = learnnum;
    }

    public String getIsrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(String isrecommend) {
        this.isrecommend = isrecommend;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
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

    public Long getCoursecategid() {
        return coursecategid;
    }

    public void setCoursecategid(Long coursecategid) {
        this.coursecategid = coursecategid;
    }

    public String getCourseCoverUrl() {
        return courseCoverUrl;
    }

    public void setCourseCoverUrl(String courseCoverUrl) {
        this.courseCoverUrl = courseCoverUrl;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
