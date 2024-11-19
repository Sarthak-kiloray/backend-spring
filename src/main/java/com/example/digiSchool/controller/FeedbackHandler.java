package com.example.digiSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.digiSchool.dto.FeedbackDataPayload;
import com.example.digiSchool.entity.FeedbackModel;
import com.example.digiSchool.service.FeedbackUtility;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackHandler {

    @Autowired
    private FeedbackUtility feedbackService;

    // Get all feedback for a specific course by its ID
    @GetMapping("/{courseId}")
    public List<FeedbackModel> getFeedbacksForCourse(@PathVariable Long courseId) {
        return feedbackService.getFeedbacksForCourse(courseId);
    }

    // Submit new feedback for a course
    @PostMapping
    public String submitFeedback(@RequestBody FeedbackDataPayload fr) {
        return feedbackService.submitFeedback(fr);
    }
}