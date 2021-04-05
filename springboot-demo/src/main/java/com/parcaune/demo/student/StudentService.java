package com.parcaune.demo.student;

import com.parcaune.demo.exceptions.StudentAppEntityNotFoundException;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            throw new StudentAppEntityNotFoundException(String.format("Student with [%d] unfortunately not found", id));
        }
    }

    public void addNewStudent(Student student) {
        System.out.println(student);
        studentRepository.save(student);
    }

}
