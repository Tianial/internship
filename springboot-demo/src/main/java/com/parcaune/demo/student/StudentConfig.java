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
    // CLR passes through every annotations @.., and comes directly in the interface CommandlineRunner and execute what is inside.
    CommandLineRunner commandLineRunner(StudentRepository studentRepository)  // by givx  as parameter studentRepository of type StudentRepository, we are injecting the DB hier for the informations in bracket to be stored
    {
        return (args) -> {
            Student tiani =new Student("tiani","tiani@parcaune.com", LocalDate.of(2017, Month.JUNE,21),4);  // id is removed b/c it is generated automatically from the DB

            Student alice = new Student("alice","alice@parcaune.com", LocalDate.of(2018, Month.JUNE,21),3);

            studentRepository.saveAll(List.of(tiani,alice));  //to save the student list in the DB.//creates an array or list with tiani and alice inside
        };
    }
}
