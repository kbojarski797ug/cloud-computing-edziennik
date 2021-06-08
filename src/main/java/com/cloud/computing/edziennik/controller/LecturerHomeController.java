package com.cloud.computing.edziennik.controller;

import com.cloud.computing.edziennik.entity.*;
import com.cloud.computing.edziennik.repository.LecturerRepository;
import com.cloud.computing.edziennik.repository.StudentRepository;
import com.cloud.computing.edziennik.repository.SubjectRepository;
import com.google.common.base.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class LecturerHomeController {

    private LecturerRepository lecturerRepository;
    private SubjectRepository subjectRepository;
    private StudentRepository studentRepository;

    public LecturerHomeController(LecturerRepository lecturerRepository, SubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.lecturerRepository = lecturerRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/lecturer/{lecturer}")
    public String getStudentHome(Model theModel, @PathVariable("lecturer") int lecturerId) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId);
        if (lecturer == null) {
            return "redirect:/lecturer";
        }

        List<Subject> subjects = subjectRepository.findByLecturer_id(lecturerId);

        theModel.addAttribute("subjects", subjects);
        theModel.addAttribute("lecturerId", lecturerId);
        return "lecturer-home";
    }

    @GetMapping("/lecturer/{lecturer}/{subjectId}")
    public String getListOfGroupForLecturer(Model theModel,
                                            @PathVariable("lecturer") int lecturerId,
                                            @PathVariable("subjectId") int subjectId) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId);
        Subject subject = subjectRepository.findById(subjectId);

        if (lecturer == null || subject == null) {
            return "redirect:/lecturer";
        }

        List<StudentGrade> grades = subject.getGrades();
        Set<Student> students = subject.getStudents();

        List<StudentInfo> studentsInfo = new ArrayList<>();

        for (Student student : students) {
            String studentGrades = grades.stream()
                    .filter(grade -> grade.getStudent().equals(student))
                    .map(grade -> grade.getGrade().getName())
                    .collect(Collectors.joining(", "));

            if (Strings.isNullOrEmpty(studentGrades)) {
                studentGrades = "";
            }
            studentsInfo.add(new StudentInfo(student, studentGrades));
        }

        studentsInfo = studentsInfo.stream()
                .sorted(Comparator.comparing(studentInfo -> studentInfo.getStudent().getLastName()))
                .sorted(Comparator.comparing(studentInfo -> studentInfo.getStudent().getFirstName()))
                .collect(Collectors.toList());

        theModel.addAttribute("studentsInfo", studentsInfo);
        theModel.addAttribute("subject", subject);

        StudentAddGrade studentAddGrade = new StudentAddGrade();
        studentAddGrade.setSubject(subject);
        studentAddGrade.setLecturer(lecturer);
        theModel.addAttribute("studentAddGrade", studentAddGrade);

        return "lecturer-student-view";
    }

    @PostMapping("/lecturer/addGrade/{studentId}/{lecturerId}/{subjectId}")
    public String addGradeForStudent(Model theModel,
                                     @ModelAttribute("studentAddGrade") StudentAddGrade studentAddGrade,
                                     @PathVariable("studentId") int studentId,
                                     @PathVariable("lecturerId") int lecturerId,
                                     @PathVariable("subjectId") int subjectId) {

        if (studentAddGrade == null || studentId <= 0 || lecturerId <= 0 || subjectId <= 0) {
            return "redirect:/lecturer";
        }

        Student student = studentRepository.findById(studentId);
        Subject subject = subjectRepository.findById(subjectId);

        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setStudent(student);

        Grade grade = Grade.get(studentAddGrade.getGrade());

        if (grade == null) {
            return "redirect:/lecturer/" + lecturerId + "/" + subjectId;
        }

        studentGrade.setGrade(grade);

        subject.getGrades().add(studentGrade);
        subjectRepository.save(subject);

        return "redirect:/lecturer/" + lecturerId + "/" + subjectId;
    }
}
