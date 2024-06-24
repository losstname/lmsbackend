package com.losstname.lmsbackend.domain.repository;

import com.losstname.lmsbackend.domain.model.enrollment.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Repository
public interface QuizRepository extends JpaRepository<Enrollment, Long> {
}
