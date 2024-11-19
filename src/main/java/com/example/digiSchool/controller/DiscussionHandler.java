package com.example.digiSchool.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.digiSchool.dto.DiscussionDataPayload;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.DiscussionModel;
import com.example.digiSchool.service.CourseUtility;
import com.example.digiSchool.service.DiscussionUtility;

@RestController
@RequestMapping("/api/discussions")
public class DiscussionHandler {

    // Services to handle discussions and courses
    @Autowired
    private DiscussionUtility discussionService;

    @Autowired
    private CourseUtility courseService;

    // Endpoint to fetch all discussions for a specific course
    @GetMapping("/{courseId}")
    public ResponseEntity<List<DiscussionModel>> getDiscussionsAndCourse(
            @PathVariable Long courseId  // Path variable to capture course ID from the URL
    ) {
        // Fetch the course by its ID
        CourseModel course = courseService.getCourseById(courseId);

        // Retrieve all discussions related to the specified course
        List<DiscussionModel> discussions = discussionService.getDiscussionsCourse(course);

        // Return the list of discussions with HTTP status 200 (OK)
        return ResponseEntity.ok(discussions);
    }

    // Endpoint to create a new discussion message for a course
    @PostMapping("/addMessage")
    public ResponseEntity<DiscussionModel> createDiscussion(
            @RequestBody DiscussionDataPayload discussionRequest  // Request body containing discussion details
    ) {
        // Debugging output (can be removed in production)
    	System.out.println(discussionRequest.getCourse_id() + " " + discussionRequest.getName() + " " + discussionRequest.getContent());

        // Fetch the course by its ID from the request payload
        CourseModel course = courseService.getCourseById(discussionRequest.getCourse_id());

        // Create a new DiscussionModel object and set its properties
        DiscussionModel discussion = new DiscussionModel();
        discussion.setCreatedBy(discussionRequest.getName());  // Set the user who created the discussion
        discussion.setCourse(course);  // Associate the discussion with the fetched course
        discussion.setContent(discussionRequest.getContent());  // Set the content of the discussion

        // Save the new discussion and return it with HTTP status 201 (Created)
        DiscussionModel createdDiscussion = discussionService.createDiscussion(discussion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiscussion);
    }
}