package com.example.digiSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.digiSchool.entity.FeedbackModel;

public interface FeedbackStorage extends JpaRepository<FeedbackModel, Long> {
    // This interface provides CRUD operations for FeedbackModel via JpaRepository
}