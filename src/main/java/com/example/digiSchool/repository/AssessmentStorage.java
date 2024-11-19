package com.example.digiSchool.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.digiSchool.entity.AssessmentModel;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.UserModel;

public interface AssessmentStorage extends JpaRepository<AssessmentModel, Long> {

    // Find all assessments for a specific user and course
    List<AssessmentModel> findByUserAndCourse(UserModel user, CourseModel course);

    // Find all assessments for a specific user
    List<AssessmentModel> findByUser(UserModel user);
}