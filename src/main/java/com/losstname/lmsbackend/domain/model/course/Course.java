package com.losstname.lmsbackend.domain.model.course;

import com.losstname.lmsbackend.domain.model.lesson.Lesson;
import com.losstname.lmsbackend.domain.model.user.Users;
import jakarta.persistence.*;

import java.util.List;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users instructor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    public Course() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getInstructor() {
        return instructor;
    }

    public void setInstructor(Users instructor) {
        this.instructor = instructor;
    }
}
