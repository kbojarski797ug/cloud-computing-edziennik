package com.cloud.computing.edziennik.entity;

import javax.persistence.*;

@Entity
public class StudentGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Student student;

    public StudentGrade() {
    }

    public StudentGrade(Grade grade, Student student) {
        this.grade = grade;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
