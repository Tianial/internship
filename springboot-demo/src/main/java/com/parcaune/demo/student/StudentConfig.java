package com.parcaune.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student Tiani =new Student("Tiani","tiani@parcaune.com", LocalDate.of(2017, Month.JUNE,21),4);  // id is removed b/c it is generated automatically from the DB

            Student Alice = new Student("Alice","alice@parcaune.com", LocalDate.of(2018, Month.JUNE,21),3);

            repository.saveAll(List.of(Tiani,Alice));  //to save in the DB create a list


        };
    }
}
