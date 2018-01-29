package com.hxgy.wechat.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "health_score")
public class HealthScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "score")
    private float score;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "score_date")
    private Date scoreDate;
    @Column(name = "course_id")
    private Long courseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(Date scoreDate) {
        this.scoreDate = scoreDate;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
