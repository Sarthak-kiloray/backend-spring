package com.example.digiSchool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digiSchool.dto.FeedbackDataPayload;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.FeedbackModel;
import com.example.digiSchool.repository.CourseStorage;
import com.example.digiSchool.repository.FeedbackStorage;

import java.util.List;

@Service
public class FeedbackUtility {

    @Autowired
    private FeedbackStorage feedbackRepository;

    @Autowired
    private CourseStorage courseRepository;

    // Retrieve all feedback for a specific course by its ID
    public List<FeedbackModel> getFeedbacksForCourse(Long courseId) {
        CourseModel course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            return course.getFeedbacks();
        }
        return null;  // Return null if the course is not found
    }

    // Submit feedback for a specific course
    public String submitFeedback(FeedbackDataPayload fr) {
        CourseModel course = courseRepository.findById(fr.getCourse_id()).orElse(null);
        FeedbackModel feedback = new FeedbackModel();

        if (course != null) {
            feedback.setCourse(course);
            feedback.setComment(fr.getComment());
            feedbackRepository.save(feedback);
            return "Feedback submitted successfully";
        }
        return "Feedback submission failed";  // Return error message if the course is not found
    }
}