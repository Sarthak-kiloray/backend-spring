package com.example.digiSchool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.digiSchool.dto.ProgressDataPayload;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.ProgressModel;
import com.example.digiSchool.entity.UserModel;
import com.example.digiSchool.repository.CourseStorage;
import com.example.digiSchool.repository.ProgressStorage;
import com.example.digiSchool.repository.UserStorage;

@Service
public class ProgressUtility {

    @Autowired
    private ProgressStorage progressRepository;

    @Autowired
    private UserStorage userRepository;

    @Autowired
    private CourseStorage courseRepository;

    // Update the progress of a user in a course
    public ResponseEntity<String> updateProgress(ProgressDataPayload request) {
        Long userId = request.getUserId();
        Long courseId = request.getCourseId();
        float playedTime = request.getPlayedTime();
        float duration = request.getDuration();

        UserModel user = userRepository.findById(userId).orElse(null);
        CourseModel course = courseRepository.findById(courseId).orElse(null);

        if (user != null && course != null) {
            ProgressModel progress = progressRepository.findByUserAndCourse(user, course);
            if (progress != null && (progress.getPlayedTime() == 0 || progress.getPlayedTime() <= playedTime)) {
                progress.setPlayedTime(playedTime);
                progress.setDuration(duration);
                progressRepository.save(progress);
                return ResponseEntity.ok("Progress updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid played time");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or course not found");
    }

    // Retrieve the progress of a user in a specific course
	public float getProgress(Long userId, Long courseId) {
		UserModel user = userRepository.findById(userId).orElse(null);
        CourseModel course = courseRepository.findById(courseId).orElse(null);

        if (user != null && course != null) {
            ProgressModel progress = progressRepository.findByUserAndCourse(user, course);
            return progress != null ? progress.getPlayedTime() : 0;  // Return played time if progress exists
        }
		return 0;  // Return 0 if no progress is found
	}

    // Update the duration of a user's engagement with a course
	public ResponseEntity<String> updateDuration(ProgressDataPayload request) {
        Long userId = request.getUserId();
        Long courseId = request.getCourseId();
        float newDuration = request.getDuration();

        UserModel user = userRepository.findById(userId).orElse(null);
        CourseModel course = courseRepository.findById(courseId).orElse(null);

        if (user != null && course != null) {
            ProgressModel progress = progressRepository.findByUserAndCourse(user, course);

            if (progress != null) {
                progress.setDuration(newDuration);
                progressRepository.save(progress);

                return ResponseEntity.ok("Duration updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Progress not found for the given user and course");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or course not found");
        }
    }
}