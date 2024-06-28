package com.losstname.lmsbackend.interfaces.dto;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
public class EnrollmentDto {
    private Long id;
    private Long courseId;
    private Long studentId;
    private Double progress;

    public EnrollmentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }
}
