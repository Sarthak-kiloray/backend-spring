package com.example.digiSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.digiSchool.dto.ProgressDataPayload;
import com.example.digiSchool.service.ProgressUtility;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(allowCredentials = "")
public class ProgressHandler {

    @Autowired
    private ProgressUtility progressService;

    // Get the progress of a user for a specific course
    @GetMapping("/{userId}/{courseId}")
    public float getProgress(@PathVariable Long userId, @PathVariable Long courseId) {
        return progressService.getProgress(userId, courseId);
    }

    // Update the progress of a user in a course
    @PutMapping("/update-progress")
    public ResponseEntity<String> updateProgress(@RequestBody ProgressDataPayload request) {
        return progressService.updateProgress(request);
    }

    // Update the duration of time spent by a user on a course
    @PutMapping("/update-duration")
    public ResponseEntity<String> updateDuration(@RequestBody ProgressDataPayload request) {
        return progressService.updateDuration(request);
    }
}