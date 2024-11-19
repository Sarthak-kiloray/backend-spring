package com.example.digiSchool.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "progress")
public class ProgressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many progress records can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    // Many progress records can belong to one course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseModel course;

    // Time the user has spent on the course
    @Column(name = "played_time")
    private float playedTime;

    // Total duration of the course
    @Column(name = "duration")
    private float duration;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }

    public float getPlayedTime() {
        return playedTime;
    }

    public void setPlayedTime(float playedTime) {
        this.playedTime = playedTime;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}