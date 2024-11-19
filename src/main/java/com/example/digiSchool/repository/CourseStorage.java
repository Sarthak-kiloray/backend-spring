package com.example.digiSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.digiSchool.entity.CourseModel;

public interface CourseStorage extends JpaRepository<CourseModel, Long> {
    // This interface provides CRUD operations for CourseModel via JpaRepository
}