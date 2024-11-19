package com.example.digiSchool.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.DiscussionModel;
import com.example.digiSchool.repository.DiscussionStorage;

@Service
public class DiscussionUtility {

    @Autowired
    private DiscussionStorage discussionRepository;

    // Retrieve all discussions for a specific course
    public List<DiscussionModel> getDiscussionsCourse(CourseModel course) {
        return discussionRepository.findByCourse(course);
    }

    // Create a new discussion
    public DiscussionModel createDiscussion(DiscussionModel discussion) {
        return discussionRepository.save(discussion);
    }
}