package com.losstname.lmsbackend.domain.model.quiz;

import com.losstname.lmsbackend.domain.model.course.Course;
import jakarta.persistence.*;

import java.util.List;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @ElementCollection
    private List<String> questions;

    public Quiz() {
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }
}
