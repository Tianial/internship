package com.parcaune.demo.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudents(){
        return List.of(new Student(1L,"Tiani","tiani@parcaune.com", LocalDate.of(2018, Month.JUNE,21),3));
    }

}
