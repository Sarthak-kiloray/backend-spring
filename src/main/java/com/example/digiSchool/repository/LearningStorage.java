package com.example.digiSchool.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.LearningModel;
import com.example.digiSchool.entity.UserModel;

public interface LearningStorage extends JpaRepository<LearningModel, Long> {

    // Find a specific learning record for a user and course
    LearningModel findByUserAndCourse(UserModel user, CourseModel course);

    // Find all learning records for a specific course by its ID
    List<LearningModel> findByCourseId(Long courseId);
}