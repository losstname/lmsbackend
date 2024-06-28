package com.losstname.lmsbackend.interfaces.dto;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
public class LessonDto {
    private Long id;
    private String title;
    private String content;
    private Long courseId;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
