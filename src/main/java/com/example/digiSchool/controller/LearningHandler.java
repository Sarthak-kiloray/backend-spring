package com.example.digiSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.digiSchool.dto.EnrollDataPayload;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.LearningModel;
import com.example.digiSchool.service.LearningUtility;

import java.util.List;

@RestController
@RequestMapping("/api/learning")
public class LearningHandler {

    @Autowired
    private LearningUtility learningService;

    // Get all courses a user is currently learning
    @GetMapping("/{userId}")
    public List<CourseModel> getLearningCourses(@PathVariable Long userId) {
        return learningService.getLearningCourses(userId);
    }

    // Get all enrollments in the system
    @GetMapping
    public List<LearningModel> getEnrollments() {
        return learningService.getEnrollments();
    }

    // Enroll a user in a course
    @PostMapping
    public String enrollCourse(@RequestBody EnrollDataPayload enrollRequest) {
        System.out.println(enrollRequest.getCourseId() + " = " + enrollRequest.getUserId());
        return learningService.enrollCourse(enrollRequest);
    }

    // Unenroll a user from a course
    @DeleteMapping("/{id}")
    public void unenrollCourse(@PathVariable Long id) {
        learningService.unenrollCourse(id);
    }
}