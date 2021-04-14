package com.parcaune.demo.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@RestController  // for it to undergo http request
@RequestMapping(path = "/management/api/v1/student")
public class StudentManagementController {

    private static final List<Student>STUDENTS = Arrays.asList(
            new Student("Tiani","tiani@parcaune.com", LocalDate.of(2017, Month.JUNE,21),4),
            new Student("Alice","alice@parcaune.com", LocalDate.of(2018, Month.JUNE,21),4)
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public static List<Student> getStudents(Principal user) {
        System.out.println("getStudents");
        return STUDENTS;
    }


    @PostMapping
    @PreAuthorize("hasAuthority('student : write')")
    public void registerNewStudent(@RequestBody Student student) // @RequestBody maps the student to be created in postman(Jason) into Java Student since they have the same attributes
    {
        System.out.println("registerNewStudent");
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student : write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId ){
        System.out.println("deleteStudent");
        System.out.println(studentId);
    }

    @PutMapping (path = "{studentId}")
    @PreAuthorize("hasAuthority('student : write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        System.out.println("updateStudent");
        System.out.println(String.format("%s,%s",studentId,student));
    }
}
