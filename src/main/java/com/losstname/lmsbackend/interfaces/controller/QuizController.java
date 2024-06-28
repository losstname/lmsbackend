package com.losstname.lmsbackend.interfaces.controller;

import com.losstname.lmsbackend.application.service.QuizService;
import com.losstname.lmsbackend.interfaces.dto.QuizDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping
    public List<QuizDto> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/course/{courseId}")
    public List<QuizDto> getQuizzesByCourse(@PathVariable Long courseId) {
        return quizService.getQuizzesByCourse(courseId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuizById(@PathVariable Long id) {
        QuizDto quiz = quizService.getQuizById(id);
        return quiz != null ? ResponseEntity.ok(quiz) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public QuizDto createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizDto> updateQuiz(@PathVariable Long id, @RequestBody QuizDto quizDto) {
        QuizDto updatedQuiz = quizService.updateQuiz(id, quizDto);
        return updatedQuiz != null ? ResponseEntity.ok(updatedQuiz) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}
