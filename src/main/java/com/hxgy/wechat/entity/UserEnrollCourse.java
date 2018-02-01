package com.hxgy.wechat.entity;



import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "course_category_id")
    private Long courseCategoryId;

    @Column(name = "total_period")
    private Long totalPeriod;
    @Column(name = "learn_period")
    private Long learnPeriod;

    @Column(name = "order_num")
    private Integer orderNo;

    @Column(name = "pay")
    private int pay;

    @Column(name = "order_date")
    private Date orderDate;


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }
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

    public Long getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(Long courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
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

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}
