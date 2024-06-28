package com.losstname.lmsbackend.domain.repository;

import com.losstname.lmsbackend.domain.model.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCourseId(Long courseId);
}
