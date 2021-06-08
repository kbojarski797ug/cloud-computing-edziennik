package com.cloud.computing.edziennik.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "group_name")
    private String groupName;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<StudentGrade> grades;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public List<StudentGrade> getGrades() {
        return grades;
    }

    public void setGrades(List<StudentGrade> grades) {
        this.grades = grades;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}

