package com.hxgy.wechat.entity;



import javax.persistence.*;
@Entity
@Table(name = "user_enroll_course")
public class UserEnrollCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "userId")
    private Long userId;
    @Column(name = "courseId")
    private Long courseId;
    @Column(name = "courseCategId")
    private Long courseCategId;
    @Column(name = "totalPeriod")
    private Long totalPeriod;
    @Column(name = "learnPeriod")
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
