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

   
}
