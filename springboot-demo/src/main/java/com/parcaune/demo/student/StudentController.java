package com.parcaune.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

//recieves the Http requests and sends it to the service to executes it


@RestController  // for it to undergo http request
@RequestMapping(path = "/api/v1/student")
public class StudentController {

    private final StudentService studentService;


    @Autowired  // used here to instantiate studentService created above
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId ){
        System.out.println("deleteStudent");
        System.out.println(studentId);
        studentService.deleteStudent(studentId);
    }

}
