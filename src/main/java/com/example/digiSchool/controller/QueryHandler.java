package com.example.digiSchool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.digiSchool.dto.QueryDataPayload;
import com.example.digiSchool.entity.CourseModel;
import com.example.digiSchool.entity.QueryModel;
import com.example.digiSchool.repository.CourseStorage;
import com.example.digiSchool.repository.QueryStorage;

@RestController
@RequestMapping("/api/questions")
public class QueryHandler {

    private final QueryStorage questionRepository;
    private final CourseStorage courseRepository;

    @Autowired
    public QueryHandler(QueryStorage questionRepository, CourseStorage courseRepository) {
        this.questionRepository = questionRepository;
        this.courseRepository = courseRepository;
    }

    // Add a new question to the course
    @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody QueryDataPayload questionRequest) {
        CourseModel course = courseRepository.findById(questionRequest.getCourseId()).orElse(null);

        // Create a new question and populate it with data from the request
        QueryModel question = new QueryModel();
        question.setQuestion(questionRequest.getQuestion());
        question.setOption1(questionRequest.getOption1());
        question.setOption2(questionRequest.getOption2());
        question.setOption3(questionRequest.getOption3());
        question.setOption4(questionRequest.getOption4());
        question.setAnswer(questionRequest.getAnswer());
        question.setCourse(course);

        // Save the question in the repository
        questionRepository.save(question);

        return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
    }

    // Get all questions for a specific course
    @GetMapping("/{courseId}")
    public ResponseEntity<List<QueryModel>> getAllQuestionsForCourse(@PathVariable Long courseId) {
        CourseModel course = courseRepository.findById(courseId).orElse(null);

        if (course != null) {
            List<QueryModel> questions = questionRepository.findByCourse(course);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}