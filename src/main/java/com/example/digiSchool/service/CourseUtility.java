package com.example.digiSchool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.LearningModel;
import com.example.digiSchool.entity.UserModel;
import com.example.digiSchool.repository.CourseStorage;
import com.example.digiSchool.repository.LearningStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseUtility {

    @Autowired
    private CourseStorage courseRepository;

    @Autowired
    private LearningStorage learningRepository;

    // Retrieve all courses
    public List<CourseModel> getAllCourses() {
        return courseRepository.findAll();
    }

    // Retrieve a specific course by its ID
    public CourseModel getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    // Create a new course
    public CourseModel createCourse(CourseModel course) {
        return courseRepository.save(course);
    }

    // Update an existing course by its ID
    public CourseModel updateCourse(Long id, CourseModel updatedCourse) {
        CourseModel existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse != null) {
            existingCourse.setCourseName(updatedCourse.getCourseName());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setPhoto(updatedCourse.getPhoto());
            existingCourse.setTutor(updatedCourse.getTutor());
            existingCourse.setVideo(updatedCourse.getVideo());
            return courseRepository.save(existingCourse);
        }
        return null;  // Return null if the course doesn't exist
    }

    // Delete a course by its ID
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    // Get all students enrolled in a specific course by its ID
    public List<UserModel> getStudentsByCourseId(Long courseId) {
        List<LearningModel> learnings = learningRepository.findByCourseId(courseId);
        return learnings.stream()
                .map(LearningModel::getUser)  // Extract the User from each Learning entry
                .filter(user -> user.getRole() == UserModel.Role.Student)  // Filter only students
                .collect(Collectors.toList());
    }
}