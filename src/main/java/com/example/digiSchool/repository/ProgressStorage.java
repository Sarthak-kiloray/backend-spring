package com.example.digiSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.ProgressModel;
import com.example.digiSchool.entity.UserModel;

public interface ProgressStorage extends JpaRepository<ProgressModel, Long> {

    // Find the progress record for a specific user and course
    ProgressModel findByUserAndCourse(UserModel user, CourseModel course);
}