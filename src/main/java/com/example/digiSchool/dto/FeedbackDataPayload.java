package com.example.digiSchool.dto;

public class FeedbackDataPayload {

    private Long course_id;
    private String comment;

    // Getter for course_id
    public Long getCourse_id() {
        return course_id;
    }

    // Setter for course_id
    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    // Getter for comment
    public String getComment() {
        return comment;
    }

    // Setter for comment
    public void setComment(String comment) {
        this.comment = comment;
    }
}