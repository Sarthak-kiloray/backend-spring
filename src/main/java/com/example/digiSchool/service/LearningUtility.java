package com.example.digiSchool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digiSchool.dto.EnrollDataPayload;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.LearningModel;
import com.example.digiSchool.entity.ProgressModel;
import com.example.digiSchool.entity.UserModel;
import com.example.digiSchool.repository.CourseStorage;
import com.example.digiSchool.repository.LearningStorage;
import com.example.digiSchool.repository.ProgressStorage;
import com.example.digiSchool.repository.UserStorage;

import java.util.*;

@Service
public class LearningUtility {

    @Autowired
    private LearningStorage learningRepository;

    @Autowired
    private UserStorage userRepository;

    @Autowired
    private CourseStorage courseRepository;
    
    @Autowired
    private ProgressStorage progressRepository;

    // Get all courses a user is currently enrolled in
    public List<CourseModel> getLearningCourses(Long userId) {
        Optional<UserModel> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            List<CourseModel> learningCourses = new ArrayList<>();

            // Loop through the user's learning records and extract the courses
            for (LearningModel learning : user.getLearningCourses()) {
                CourseModel course = learning.getCourse();
                learningCourses.add(course);
            }

            return learningCourses;
        }

        return null;  // Return null if the user is not found
    }
    
    // Get all enrollments in the system
    public List<LearningModel> getEnrollments() {
        return learningRepository.findAll();
    }

    // Enroll a user in a course
    public String enrollCourse(EnrollDataPayload enrollRequest) {
        UserModel user = userRepository.findById(enrollRequest.getUserId()).orElse(null);
        CourseModel course = courseRepository.findById(enrollRequest.getCourseId()).orElse(null);

        if (user != null && course != null) {
            // Check if the user is already enrolled in the course
            LearningModel existingLearning = learningRepository.findByUserAndCourse(user, course);
            if (existingLearning != null) {
                return "Course already enrolled";
            }

            // Create a new progress record for the course
            ProgressModel progress = new ProgressModel();
            progress.setUser(user);
            progress.setCourse(course);
            progressRepository.save(progress);

            // Create a new learning record for the enrollment
            LearningModel learning = new LearningModel();
            learning.setUser(user);
            learning.setCourse(course);
            learningRepository.save(learning);

            return "Enrolled successfully";
        }

        return "Failed to enroll";  // Return failure message if user or course is not found
    }

    // Unenroll a user from a course by its ID
    public void unenrollCourse(Long id) {
        learningRepository.deleteById(id);
    }
}