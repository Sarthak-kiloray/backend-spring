package com.example.digiSchool.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.digiSchool.entity.AssessmentModel;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.UserModel;
import com.example.digiSchool.repository.AssessmentStorage;

@Service
public class AssessmentUtility {

    @Autowired
    private AssessmentStorage assessmentRepository;

    // Get assessments for a specific user and course
    public List<AssessmentModel> getAssessmentsByUserAndCourse(UserModel user, CourseModel course) {
        return assessmentRepository.findByUserAndCourse(user, course);
    }

    // Get all assessments for a specific user
    public ResponseEntity<List<AssessmentModel>> getAssessmentByUser(UserModel user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assessmentRepository.findByUser(user));
    }

    // Create a new assessment or update an existing one
    public AssessmentModel createAssessment(AssessmentModel assessment) {
        return assessmentRepository.save(assessment);
    }

    // Add or update marks for an assessment
    public void addMarks(AssessmentModel assessment, int marks) {
        assessment.setMarks(marks);
    }

    // Save or update an assessment for a user and course
    public ResponseEntity<AssessmentModel> saveAssessment(UserModel user, CourseModel course, AssessmentModel assessment) {
        List<AssessmentModel> existingAssessments = getAssessmentsByUserAndCourse(user, course);

        if (!existingAssessments.isEmpty()) {
            // If an assessment already exists for the user and course, update the marks if they are higher
            AssessmentModel existingAssessment = existingAssessments.get(0);
            int newMarks = assessment.getMarks();

            if (newMarks > existingAssessment.getMarks()) {
                addMarks(existingAssessment, newMarks);
                AssessmentModel updatedAssessment = createAssessment(existingAssessment);
                return ResponseEntity.status(HttpStatus.CREATED).body(updatedAssessment);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Reject if the new marks are not higher
            }
        } else {
            // If no previous assessment exists, create a new one
            assessment.setUser(user);
            assessment.setCourse(course);
            AssessmentModel savedAssessment = createAssessment(assessment);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAssessment);
        }
    }
}