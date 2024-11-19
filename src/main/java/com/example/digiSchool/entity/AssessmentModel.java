package com.example.digiSchool.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "assessment")
public class AssessmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many assessments can belong to one course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseModel course;

    // Many assessments can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    private int marks;

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for course
    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }

    // Getter and Setter for user
    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    // Getter and Setter for marks
    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}