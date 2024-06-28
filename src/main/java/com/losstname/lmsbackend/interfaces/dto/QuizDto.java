package com.losstname.lmsbackend.interfaces.dto;

import java.util.List;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
public class QuizDto {
    private Long id;
    private String title;
    private Long courseId;
    private List<String> questions;

    public QuizDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }
}
