package com.hxgy.wechat.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zy
 * @create 2018-01-23 9:31
 **/
@Entity
@Table(name = "health_course_desc")
public class HealthCourseDesc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "course_desc")
    private String courseDesc;

    @Column(name = "course_price")
    private String coursePrice;

    @Column(name = "course_cover_url")
    private String courseCoverUrl;

    @Column(name = "for_crowd")
    private String forCrowd;

    @Column(name = "learn_num")
    private Long learnNum;

    @Column(name = "recommend")
    private Integer recommend;

    @Column(name = "score")
    private float score;

    @Column(name = "enable")
    private Integer enable;

    @Column(name = "course_category_id")
    private Long courseCategoryId;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_dept_id")
    private Integer authorDeptId;

    @Column(name = "author_dept_name")
    private String authorDeptName;

    @Column(name = "author_hosp_name")
    private String authorHospName;

    @Column(name = "author_hosp_id")
    private Integer authorHospId;

    @Column(name = "category")
    private Integer category;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseCoverUrl() {
        return courseCoverUrl;
    }

    public void setCourseCoverUrl(String courseCoverUrl) {
        this.courseCoverUrl = courseCoverUrl;
    }

    public String getForCrowd() {
        return forCrowd;
    }

    public void setForCrowd(String forCrowd) {
        this.forCrowd = forCrowd;
    }

    public Long getLearnNum() {
        return learnNum;
    }

    public void setLearnNum(Long learnNum) {
        this.learnNum = learnNum;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Long getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(Long courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getAuthorDeptId() {
        return authorDeptId;
    }

    public void setAuthorDeptId(Integer authorDeptId) {
        this.authorDeptId = authorDeptId;
    }

    public String getAuthorDeptName() {
        return authorDeptName;
    }

    public void setAuthorDeptName(String authorDeptName) {
        this.authorDeptName = authorDeptName;
    }

    public String getAuthorHospName() {
        return authorHospName;
    }

    public void setAuthorHospName(String authorHospName) {
        this.authorHospName = authorHospName;
    }

    public Integer getAuthorHospId() {
        return authorHospId;
    }

    public void setAuthorHospId(Integer authorHospId) {
        this.authorHospId = authorHospId;
    }
}
