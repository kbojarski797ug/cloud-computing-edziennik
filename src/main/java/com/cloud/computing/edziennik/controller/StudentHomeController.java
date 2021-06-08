package com.cloud.computing.edziennik.controller;

import com.cloud.computing.edziennik.entity.Student;
import com.cloud.computing.edziennik.entity.StudentHomeData;
import com.cloud.computing.edziennik.entity.Subject;
import com.cloud.computing.edziennik.repository.StudentRepository;
import com.cloud.computing.edziennik.repository.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudentHomeController {

    private SubjectRepository subjectRepository;
    private StudentRepository studentRepository;

    public StudentHomeController(SubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student/{studentId}")
    public String getStudentHome(Model theModel, @PathVariable("studentId") int studentId) {
        Student student = studentRepository.findById(studentId);

        if (student == null) {
            return "redirect:/";
        }

        List<Subject> subjects = subjectRepository.findByStudents_id(studentId);
        List<StudentHomeData> studentDataList = new ArrayList<>();


        for (Subject subject : subjects) {
            if (subject.getGrades() != null) {
                String grades = subject.getGrades().stream()
                        .filter(grade -> grade.getStudent() != null && grade.getStudent().getId() == studentId)
                        .map(grade -> grade.getGrade().getName())
                        .collect(Collectors.joining(", "));

                studentDataList.add(new StudentHomeData(subject.getName(), subject.getLecturer().getFullName(), grades));
            }
        }

        theModel.addAttribute("student", student);
        theModel.addAttribute("studentDataList", studentDataList);

        return "student-home";
    }
}