package com.cloud.computing.edziennik.controller;

import com.cloud.computing.edziennik.entity.*;
import com.cloud.computing.edziennik.repository.LecturerRepository;
import com.cloud.computing.edziennik.repository.StudentGradeRespository;
import com.cloud.computing.edziennik.repository.StudentRepository;
import com.cloud.computing.edziennik.repository.SubjectRepository;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

import static com.cloud.computing.edziennik.entity.Grade.*;

@Controller
public class LoginController {

    private SubjectRepository subjectRepository;
    private StudentRepository studentRepository;
    private LecturerRepository lecturerRepository;
    private StudentGradeRespository studentGradeRespository;

    public LoginController(SubjectRepository subjectRepository, StudentRepository studentRepository, LecturerRepository lecturerRepository, StudentGradeRespository studentGradeRespository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
        this.studentGradeRespository = studentGradeRespository;
    }

    @GetMapping("/")
    public String login(Model theModel) {
        // providing test data
        if (Lists.newArrayList(studentRepository.findAll()).isEmpty()) {
            mockDataInDatabase();
        }

        LoginData loginData = new LoginData();
        theModel.addAttribute("loginData", loginData);

        return "login";
    }

    @PostMapping("/processlogin")
    public String processLogin(@ModelAttribute(value = "loginData") LoginData loginData) {

        Student student = studentRepository.findByPeselAndPassword(loginData.getPesel(), loginData.getPassword());
        if (student == null) {
            return "redirect:/";
        }

        return "redirect:/student/" + student.getId();
    }

    @GetMapping("/lecturer")
    public String loginLecturer(Model theModel) {
        //TO REMOVE
        if (Lists.newArrayList(lecturerRepository.findAll()).isEmpty()) {
            mockDataInDatabase();
        }

        LoginData loginData = new LoginData();
        theModel.addAttribute("loginData", loginData);

        return "lecturer-login";
    }

    @PostMapping("/lecturer/processlogin")
    public String lecturerProcessLogin(@ModelAttribute(value = "loginData") LoginData loginData) {

        Lecturer lecturer = lecturerRepository.findByPeselAndPassword(loginData.getPesel(), loginData.getPassword());
        if (lecturer == null) {
            return "redirect:/lecturer";
        }

        return "redirect:/lecturer/" + lecturer.getId();
    }

    private void mockDataInDatabase() {
        Lecturer lecturer = new Lecturer();
        lecturer.setFirstName("Maciej");
        lecturer.setLastName("Nowak");
        lecturer.setPesel("11");
        lecturer.setPassword("11");
        lecturerRepository.save(lecturer);

        Lecturer lecturer2 = new Lecturer();
        lecturer2.setFirstName("Adam");
        lecturer2.setLastName("Kowalski");
        lecturer2.setPesel("22");
        lecturer2.setPassword("22");
        lecturerRepository.save(lecturer2);

        Student student = new Student();
        student.setFirstName("Kamil");
        student.setLastName("Nowak");
        student.setPesel("1");
        student.setPassword("1");
        student.setGroupName("S52-05");
        studentRepository.save(student);

        Student student2 = new Student();
        student2.setFirstName("Weronika");
        student2.setLastName("Kowalska");
        student2.setPesel("2");
        student2.setPassword("2");
        student2.setGroupName("S52-05");
        studentRepository.save(student2);

        Student student3 = new Student();
        student3.setFirstName("Mariusz");
        student3.setLastName("Okrzepka");
        student3.setPesel("3");
        student3.setPassword("3");
        student3.setGroupName("S52-05");
        studentRepository.save(student3);

        Student student4 = new Student();
        student4.setFirstName("Bożena");
        student4.setLastName("Kamińska");
        student4.setPesel("4");
        student4.setPassword("4");
        student4.setGroupName("S52-05");
        studentRepository.save(student4);

        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setGrade(FIVE);
        studentGrade.setStudent(student);
        studentGradeRespository.save(studentGrade);

        StudentGrade studentGrade2 = new StudentGrade();
        studentGrade2.setGrade(ONE);
        studentGrade2.setStudent(student);
        studentGradeRespository.save(studentGrade2);

        StudentGrade studentGrade3 = new StudentGrade();
        studentGrade3.setGrade(FOUR_PLUS);
        studentGrade3.setStudent(student);
        studentGradeRespository.save(studentGrade3);

        StudentGrade studentGrade4 = new StudentGrade();
        studentGrade4.setGrade(TWO);
        studentGrade4.setStudent(student2);
        studentGradeRespository.save(studentGrade4);

        StudentGrade studentGrade5 = new StudentGrade();
        studentGrade5.setGrade(TREE);
        studentGrade5.setStudent(student2);
        studentGradeRespository.save(studentGrade5);

        StudentGrade studentGrade6 = new StudentGrade();
        studentGrade6.setGrade(SIX);
        studentGrade6.setStudent(student3);
        studentGradeRespository.save(studentGrade6);

        StudentGrade studentGrade7 = new StudentGrade();
        studentGrade7.setGrade(TREE_MINUS);
        studentGrade7.setStudent(student3);
        studentGradeRespository.save(studentGrade7);

        StudentGrade studentGrade8 = new StudentGrade();
        studentGrade8.setGrade(FOUR);
        studentGrade8.setStudent(student4);
        studentGradeRespository.save(studentGrade8);

        StudentGrade studentGrade9 = new StudentGrade();
        studentGrade9.setGrade(FOUR_PLUS);
        studentGrade9.setStudent(student4);
        studentGradeRespository.save(studentGrade9);


        Subject przyroda = new Subject();
        przyroda.setName("Przyroda");
        przyroda.setGroupName("S52-05");
        przyroda.setLecturer(lecturer);
        przyroda.setGrades(Arrays.asList(new StudentGrade(ONE, student),
                new StudentGrade(TWO, student2), new StudentGrade(ONE, student3), new StudentGrade(FIVE_PLUS, student4), new StudentGrade(SIX, student4)));
        przyroda.setStudents(Sets.newHashSet(student, student2, student3, student4));
        subjectRepository.save(przyroda);

        Subject geografia = new Subject();
        geografia.setName("Geografia");
        geografia.setGroupName("S52-05");
        geografia.setLecturer(lecturer);
        geografia.setGrades(Arrays.asList(new StudentGrade(SIX, student),
                new StudentGrade(FOUR_MINUS, student2), new StudentGrade(TREE_MINUS, student3), new StudentGrade(FIVE_PLUS, student4), new StudentGrade(ONE, student4)));
        geografia.setStudents(Sets.newHashSet(student, student2, student3, student4));
        subjectRepository.save(geografia);

        Subject informatyka = new Subject();
        informatyka.setName("Informatyka");
        informatyka.setGroupName("S52-05");
        informatyka.setLecturer(lecturer2);
        informatyka.setGrades(Arrays.asList(new StudentGrade(SIX, student),
                new StudentGrade(SIX, student2), new StudentGrade(TREE_PLUS, student3), new StudentGrade(FOUR_MINUS, student4), new StudentGrade(TWO_PLUS, student4)));
        informatyka.setStudents(Sets.newHashSet(student, student2, student3, student4));
        subjectRepository.save(informatyka);
    }
}