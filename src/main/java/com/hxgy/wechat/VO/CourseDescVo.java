package com.hxgy.wechat.VO;

/**
 * @author zy
 * @create 2018-01-24 11:13
 **/
public class CourseDescVo {

    private Long id;

    private Integer recommend;

    private String coursePrice;

    private Long courseCategoryId;

    private String courseName;

    private String forCrowd;

    private String courseCode;

    private Integer enable;

    public String getForCrowd() {
        return forCrowd;
    }

    public void setForCrowd(String forCrowd) {
        this.forCrowd = forCrowd;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Long getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(Long courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
