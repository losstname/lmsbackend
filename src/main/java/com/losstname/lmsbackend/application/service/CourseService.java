package com.losstname.lmsbackend.application.service;

import com.losstname.lmsbackend.domain.model.course.Course;
import com.losstname.lmsbackend.domain.model.lesson.Lesson;
import com.losstname.lmsbackend.domain.repository.CourseRepository;
import com.losstname.lmsbackend.domain.repository.LessonRepository;
import com.losstname.lmsbackend.interfaces.dto.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final LessonService lessonService;

    @Autowired
    public CourseService(CourseRepository courseRepository, LessonRepository lessonRepository, LessonService lessonService) {
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
        this.lessonService = lessonService;
    }

    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<CourseDto> getCoursesByInstructor(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CourseDto getCourseById(Long id) {
        return courseRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    public CourseDto createCourse(CourseDto courseDto) {
        Course course = convertToEntity(courseDto);
        Course savedCourse = courseRepository.save(course);
        return convertToDto(savedCourse);
    }

    public CourseDto updateCourse(Long id, CourseDto courseDto) {
        return courseRepository.findById(id).map(course -> {
            course.setTitle(courseDto.getTitle());
            course.setDescription(courseDto.getDescription());
            Course updatedCourse = courseRepository.save(course);
            return convertToDto(updatedCourse);
        }).orElse(null);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    private CourseDto convertToDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setTitle(course.getTitle());
        courseDto.setDescription(course.getDescription());
        courseDto.setInstructorId(course.getInstructor().getId());

        List<Lesson> lessons = lessonRepository.findAllByCourse(course);
        courseDto.setLessons(lessons.stream().map(lessonService::convertToDto).collect(Collectors.toList()));

        return courseDto;
    }

    private Course convertToEntity(CourseDto courseDto) {
        Course course = new Course();
        course.setId(courseDto.getId());
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        return course;
    }
}
