package com.example.digiSchool.dto;

public class ProgressDataPayload {

    private Long userId;
    private Long courseId;
    private float playedTime;
    private float duration;

    // Getter for userId
    public Long getUserId() {
        return userId;
    }

    // Setter for userId
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Getter for courseId
    public Long getCourseId() {
        return courseId;
    }

    // Setter for courseId
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    // Getter for playedTime (time the user has spent on the course)
    public float getPlayedTime() {
        return playedTime;
    }

    // Setter for playedTime
    public void setPlayedTime(float playedTime) {
        this.playedTime = playedTime;
    }

    // Getter for total duration of the course
    public float getDuration() {
        return duration;
    }

    // Setter for duration
    public void setDuration(float duration) {
        this.duration = duration;
    }
}