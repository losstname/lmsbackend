package com.losstname.lmsbackend.domain.repository;

import com.losstname.lmsbackend.domain.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstructorId(Long instructorId);
}
