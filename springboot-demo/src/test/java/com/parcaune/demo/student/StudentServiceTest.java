package com.parcaune.demo.student;


//NB: any time we use the studentrepository for tests,we mock it in the student service

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) //
class StudentServiceTest {

@Mock   // instantiate studentRepository(not a real instance,a fake instance)
    private StudentRepository studentRepository;
    //private  AutoCloseable autoCloseable;
    private StudentService underTest;

    @BeforeEach  // line 11 and 12 will be run before each Test
    void setUp() {

        //MockitoAnnotations.openMocks(this);// in order to initialise the mocks. in case of many mocks,all mocks will be initiallise in here
        // autoCloseable = MockitoAnnotations.openMocks(this);// in order to initialise the mocks. in case of many mocks,all mocks will be initiallise in here
        underTest = new StudentService(studentRepository);
    }

   // @AfterEach  // line 27 and 28 will be run after each Test
   // void tearDown() throws Exception {

       // autoCloseable.close();
    //}


    @Test
    void canGetStudents() {
        //When
        underTest.getStudents();
        //Then
        verify(studentRepository).findAll(); // findAll() only because it is used in the method getStudents() in the StudentService class
    }

    @Test
    @Disabled
    void getStudentById() {

    }

    @Test
    @Disabled
    void addNewStudent() {
    }

    @Test
    @Disabled // disables the test
    void deleteStudent() {
    }
}
