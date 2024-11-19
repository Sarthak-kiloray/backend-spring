package com.example.digiSchool.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.digiSchool.entity.AssessmentModel;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.UserModel;
import com.example.digiSchool.service.AssessmentUtility;
import com.example.digiSchool.service.CourseUtility;
import com.example.digiSchool.service.UserUtility;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentHandler {

    // Service classes to handle business logic
    @Autowired
    private AssessmentUtility assessmentService;

    @Autowired
    private UserUtility userService;

    @Autowired
    private CourseUtility courseService;

    // Endpoint to fetch assessments for a specific user and course
    @GetMapping("/user/{userId}/course/{courseId}")
    public ResponseEntity<List<AssessmentModel>> getAssessmentsByUserAndCourse(
            @PathVariable Long userId,  
            @PathVariable Long courseId  
    ) {
        // Fetch the user and course by their IDs
        UserModel user = userService.getUserById(userId);
        CourseModel course = courseService.getCourseById(courseId);

        // Get assessments for the specific user and course
        List<AssessmentModel> assessments = assessmentService.getAssessmentsByUserAndCourse(user, course);

        // Return the list of assessments with HTTP status 200 (OK)
        return ResponseEntity.ok(assessments);
    }

    // Endpoint to fetch all assessments for a specific user based on performance
    @GetMapping("/perfomance/{userId}")
    public ResponseEntity<List<AssessmentModel>> getAssessmentsByUser(@PathVariable Long userId){
        // Fetch the user by their ID
        UserModel user = userService.getUserById(userId);

        // Return the user's assessments wrapped in a ResponseEntity
        return assessmentService.getAssessmentByUser(user);
    }

    // Endpoint to add a new assessment for a specific user and course
    @PostMapping("/add/{userId}/{courseId}")
    public ResponseEntity<AssessmentModel> addAssessmentWithMarks(
            @PathVariable Long userId,  
            @PathVariable Long courseId,  
            @RequestBody AssessmentModel assessment) {  // Assessment details from request body

        // Fetch the user and course by their IDs
        UserModel user = userService.getUserById(userId);
        CourseModel course = courseService.getCourseById(courseId);

        // Save the new assessment for the specified user and course, then return it in the response
        return assessmentService.saveAssessment(user , course, assessment);
    }
}