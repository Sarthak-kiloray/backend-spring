package com.example.digiSchool.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.UserModel;
import com.example.digiSchool.service.CourseUtility;

@RestController
@RequestMapping("/api/courses")
public class CourseHandler {

    // CourseUtility service to handle business logic related to courses
    @Autowired
    private CourseUtility courseService;

    // Endpoint to retrieve all courses
    @GetMapping
    public List<CourseModel> getAllCourses() {
        // Fetch and return the list of all available courses
        return courseService.getAllCourses();
    }

    // Endpoint to retrieve a specific course by its ID
    @GetMapping("/{id}")
    public CourseModel getCourseById(@PathVariable Long id) {
        // Fetch and return the course based on the provided ID
        return courseService.getCourseById(id);
    }

    // Endpoint to create a new course
    @PostMapping
    public CourseModel createCourse(@RequestBody CourseModel course) {
        // Save the new course and return the created course object
        return courseService.createCourse(course);
    }

    // Endpoint to update an existing course by its ID
    @PostMapping("/{id}")
    public CourseModel updateCourse(@PathVariable Long id, @RequestBody CourseModel updatedCourse) {
        // Update the course with the provided ID and return the updated course object
        return courseService.updateCourse(id, updatedCourse);
    }

    // Endpoint to delete a specific course by its ID
    @GetMapping("/delete/{id}")
    public void deleteCourse(@PathVariable Long id) {
        // Delete the course based on the provided ID
        courseService.deleteCourse(id);
    }

    // Endpoint to retrieve all students enrolled in a specific course by its ID
    @GetMapping("/{courseId}/students")
    public List<UserModel> getStudentsByCourseId(@PathVariable Long courseId) {
        // Fetch and return the list of students enrolled in the specified course
        return courseService.getStudentsByCourseId(courseId);
    }
}