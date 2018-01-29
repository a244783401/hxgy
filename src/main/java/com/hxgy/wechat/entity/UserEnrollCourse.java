package com.hxgy.wechat.entity;



import javax.persistence.*;
@Entity
@Table(name = "user_enroll_course")
public class UserEnrollCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "course_categ_id")
    private Long courseCategId;
    @Column(name = "total_period")
    private Long totalPeriod;
    @Column(name = "learn_period")
    private Long learnPeriod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseCategId() {
        return courseCategId;
    }

    public void setCourseCategId(Long courseCategId) {
        this.courseCategId = courseCategId;
    }

    public Long getTotalPeriod() {
        return totalPeriod;
    }

    public void setTotalPeriod(Long totalPeriod) {
        this.totalPeriod = totalPeriod;
    }

    public Long getLearnPeriod() {
        return learnPeriod;
    }

    public void setLearnPeriod(Long learnPeriod) {
        this.learnPeriod = learnPeriod;
    }
}
