package com.cloud.computing.edziennik.entity;

public class StudentHomeData {

    private String subject;

    private String lecturer;

    private String grades;

    public StudentHomeData(String subject, String lecturer, String grades) {
        this.subject = subject;
        this.lecturer = lecturer;
        this.grades = grades;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }
}
