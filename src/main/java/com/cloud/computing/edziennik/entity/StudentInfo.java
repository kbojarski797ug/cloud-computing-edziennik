package com.cloud.computing.edziennik.entity;

public class StudentInfo {

    private Student student;

    private String grades;

    public StudentInfo(Student student, String grades) {
        this.student = student;
        this.grades = grades;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }
}