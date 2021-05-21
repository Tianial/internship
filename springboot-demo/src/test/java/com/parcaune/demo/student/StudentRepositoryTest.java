package com.parcaune.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest  // Test in Repository
{

    @Autowired
    private StudentRepository underTests;

    @AfterEach
    void tearDown() {
        underTests.deleteAll();   // for each test,it has to be deleted

    }

    @Test
    void itShouldCheckIfStudentEmailExists() {
        //given

        Student student = new Student(
                "Tiani", "tiani@parcaune.com", LocalDate.of(2017, Month.JUNE, 21), 4, "345678987654", "assets/images/pic3.jpeg", "456", "student"
        );

        underTests.save(student); // saves all students

        //when
        String email = "tiani@parcaune.com";
        Boolean expected = underTests.selectExistsEmail(email); // selectExistsEmail(email) is being tested


        //then

        assertThat(expected).isTrue();

    }

    @Test
    void itShouldCheckIfStudentEmailDoesnotExists() {
        //given

        String email = "tiani@parcaune.com";


        Boolean expected = underTests.selectExistsEmail(email); // selectExistsEmail(email) is being tested


        //then

        assertThat(expected).isFalse(); // expected should be equal to false

    }

}
