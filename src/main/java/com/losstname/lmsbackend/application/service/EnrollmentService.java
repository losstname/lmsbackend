package com.losstname.lmsbackend.application.service;

import com.losstname.lmsbackend.domain.model.course.Course;
import com.losstname.lmsbackend.domain.model.enrollment.Enrollment;
import com.losstname.lmsbackend.domain.model.user.Users;
import com.losstname.lmsbackend.domain.repository.CourseRepository;
import com.losstname.lmsbackend.domain.repository.EnrollmentRepository;
import com.losstname.lmsbackend.domain.repository.UsersRepository;
import com.losstname.lmsbackend.interfaces.dto.EnrollmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Service
public class EnrollmentService {

    private EnrollmentRepository enrollmentRepository;
    private UsersRepository usersRepository;
    private CourseRepository courseRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, UsersRepository usersRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.usersRepository = usersRepository;
        this.courseRepository = courseRepository;
    }

    public List<EnrollmentDto> getAllEnrollments() {
        return enrollmentRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<EnrollmentDto> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<EnrollmentDto> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public EnrollmentDto getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    public EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = convertToEntity(enrollmentDto);
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return convertToDto(savedEnrollment);
    }

    public EnrollmentDto updateEnrollment(Long id, EnrollmentDto enrollmentDto) {
        return enrollmentRepository.findById(id).map(enrollment -> {
            enrollment.setProgress(enrollmentDto.getProgress());
            Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
            return convertToDto(updatedEnrollment);
        }).orElse(null);
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    private EnrollmentDto convertToDto(Enrollment enrollment) {
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setId(enrollment.getId());
        enrollmentDto.setCourseId(enrollment.getCourse().getId());
        enrollmentDto.setStudentId(enrollment.getStudent().getId());
        enrollmentDto.setProgress(enrollment.getProgress());
        return enrollmentDto;
    }

    private Enrollment convertToEntity(EnrollmentDto enrollmentDto) {
        Course course = courseRepository.findById(enrollmentDto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course with: " + enrollmentDto.getCourseId() + " not found"));

        Users users = usersRepository.findById(enrollmentDto.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setId(enrollmentDto.getId());
        enrollment.setCourse(course);
        enrollment.setStudent(users);
        enrollment.setProgress(enrollmentDto.getProgress());
        return enrollment;
    }
}
