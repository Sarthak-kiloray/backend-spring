package com.example.digiSchool.dto;

public class EnrollDataPayload {

    private Long userId;
    private Long courseId;

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
}