package com.example.digiSchool.dto;

public class DiscussionDataPayload {

    private Long course_id;
    private String content;
    private String name;

    // Getter for course_id
    public Long getCourse_id() {
        return course_id;
    }

    // Setter for course_id
    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for content
    public String getContent() {
        return content;
    }

    // Setter for content
    public void setContent(String content) {
        this.content = content;
    }
}