package com.example.digiSchool.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.DiscussionModel;

public interface DiscussionStorage extends JpaRepository<DiscussionModel, Long> {

    // Find all discussions related to a specific course
    List<DiscussionModel> findByCourse(CourseModel course);
}