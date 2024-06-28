package com.losstname.lmsbackend.application.service;

import com.losstname.lmsbackend.domain.model.quiz.Quiz;
import com.losstname.lmsbackend.domain.repository.QuizRepository;
import com.losstname.lmsbackend.interfaces.dto.QuizDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<QuizDto> getAllQuizzes() {
        return quizRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<QuizDto> getQuizzesByCourse(Long courseId) {
        return quizRepository.findByCourseId(courseId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public QuizDto getQuizById(Long id) {
        return quizRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    public QuizDto createQuiz(QuizDto quizDto) {
        Quiz quiz = convertToEntity(quizDto);
        Quiz savedQuiz = quizRepository.save(quiz);
        return convertToDto(savedQuiz);
    }

    public QuizDto updateQuiz(Long id, QuizDto quizDto) {
        return quizRepository.findById(id).map(quiz -> {
            quiz.setTitle(quizDto.getTitle());
            quiz.setQuestions(quizDto.getQuestions());
            Quiz updatedQuiz = quizRepository.save(quiz);
            return convertToDto(updatedQuiz);
        }).orElse(null);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    private QuizDto convertToDto(Quiz quiz) {
        QuizDto quizDto = new QuizDto();
        quizDto.setId(quiz.getId());
        quizDto.setTitle(quiz.getTitle());
        quizDto.setCourseId(quiz.getCourse().getId());
        quizDto.setQuestions(quiz.getQuestions());
        return quizDto;
    }

    private Quiz convertToEntity(QuizDto quizDto) {
        Quiz quiz = new Quiz();
        quiz.setId(quizDto.getId());
        quiz.setTitle(quizDto.getTitle());
        quiz.setQuestions(quizDto.getQuestions());
        return quiz;
    }
}
