package com.hxgy.wechat.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zy
 * @create 2018-01-23 9:46
 **/
@Entity
@Table(name = "health_course_item")
public class HealthCourseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "course_id")
    private Long courseId;

    private String name;

    private Integer free;

    @Column(name = "video_desc")
    private String videoDesc;

    @Column(name = "create_date")
    private Date createDate;

    private String url;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "view_num")
    private Long viewNum;

    @Column(name = "praise_num")
    private Long praiseNum;

    private Integer enable;
    @Column(name = "author_id")

    private Integer authorId;
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

    @Column(name = "item_index")
    private Integer itemIndex;

    @Column(name = "video_time")
    private String videoTime;

    private float score;

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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

    public Integer getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(Integer itemIndex) {
        this.itemIndex = itemIndex;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
