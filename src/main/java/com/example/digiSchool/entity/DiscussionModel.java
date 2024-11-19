package com.example.digiSchool.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "discussion")
public class DiscussionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discussion_id")
    private Integer discussionId;

    // Many discussions can belong to one course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseModel course;

    @Column(name = "title")
    private String title;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    // Column to store the content of the discussion, defined as TEXT for larger content
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    // Getters and Setters

    public Integer getId() {
        return discussionId;
    }

    public void setId(Integer id) {
        this.discussionId = id;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}