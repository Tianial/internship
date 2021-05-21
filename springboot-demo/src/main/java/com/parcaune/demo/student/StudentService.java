package com.parcaune.demo.student;

import com.parcaune.demo.exceptions.StudentAppBadRequestException;
import com.parcaune.demo.exceptions.StudentAppEntityNotFoundException;
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
        Boolean existsEmail = studentRepository.selectExistsEmail(student.getEmail());

        if (existsEmail) {
            throw new StudentAppBadRequestException("Email " + student.getEmail() + " taken");
        }

        studentRepository.save(student); // we want to capture the value student(recieves the capture value
    }

    public void deleteStudent(Long studentId) {
        System.out.println("Delete student with  student id" + studentId);
        studentRepository.deleteById(studentId);
    }

}
