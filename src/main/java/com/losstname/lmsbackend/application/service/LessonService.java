package com.losstname.lmsbackend.application.service;

import com.losstname.lmsbackend.domain.model.lesson.Lesson;
import com.losstname.lmsbackend.domain.repository.LessonRepository;
import com.losstname.lmsbackend.interfaces.dto.LessonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public LessonDto convertToDto(Lesson lesson) {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(lesson.getId());
        lessonDto.setTitle(lesson.getTitle());
        lessonDto.setContent(lesson.getContent());
        lessonDto.setCourseId(lesson.getCourse().getId());
        return lessonDto;
    }

    public Lesson convertToEntity(LessonDto lessonDto) {
        Lesson lesson = new Lesson();
        lesson.setId(lessonDto.getId());
        lesson.setTitle(lessonDto.getTitle());
        lesson.setContent(lessonDto.getContent());
        return lesson;
    }

    public LessonDto createLesson(LessonDto lessonDto) {
        Lesson lesson = convertToEntity(lessonDto);
        Lesson savedLesson = lessonRepository.save(lesson);
        return convertToDto(savedLesson);
    }

    public LessonDto updateLesson(Long id, LessonDto lessonDto) {
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        if (lesson == null) {
            return null;
        }
        lesson.setTitle(lessonDto.getTitle());
        lesson.setContent(lessonDto.getContent());
        Lesson updatedLesson = lessonRepository.save(lesson);
        return convertToDto(updatedLesson);
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

}
