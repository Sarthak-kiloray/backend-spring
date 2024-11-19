package com.example.digiSchool.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.QueryModel;

public interface QueryStorage extends JpaRepository<QueryModel, Long> {

    // Find all questions associated with a specific course
    List<QueryModel> findByCourse(CourseModel course);
}