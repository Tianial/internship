package com.parcaune.demo.student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@RestController  // for it to undergo http request
@RequestMapping(path = "management/api/v1/student")
public class StudentManagementController {

    private static final List<Student>STUDENTS = Arrays.asList(
            new Student("Tiani","tiani@parcaune.com", LocalDate.of(2017, Month.JUNE,21),4),
            new Student("Alice","alice@parcaune.com", LocalDate.of(2018, Month.JUNE,21),4)
    );

    public static List<Student> getSTUDENTS() {
        return STUDENTS;
    }
    public void registerNewStudent(Student student) {
        System.out.println(student);
    }
    public void deleteStudent(Integer studentId ){

    }
}
