package com.example.digiSchool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class FeedbackModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many feedbacks can belong to one course
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course_id")
    private CourseModel course;

    private String comment;

    // ID of the user receiving the feedback
    private Long to_id;

    // ID of the user giving the feedback
    private Long from_id;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getToId() {
        return to_id;
    }

    public void setToId(Long id) {
        this.to_id = id;
    }

    public Long getFromId() {
        return from_id;
    }

    public void setFromId(Long id) {
        this.from_id = id;
    }
}