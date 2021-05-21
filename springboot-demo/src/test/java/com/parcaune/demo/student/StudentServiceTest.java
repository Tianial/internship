package com.parcaune.demo.student;


//NB: any time we use the studentrepository for tests,we mock it in the student service

import com.parcaune.demo.exceptions.StudentAppBadRequestException;
import com.parcaune.demo.exceptions.StudentAppEntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) //
class StudentServiceTest {

    @Mock   // instantiate studentRepository(not a real instance, a fake instance)
    private StudentRepository studentRepository;

    //private  AutoCloseable autoCloseable;
    private StudentService underTest;

    @BeforeEach
        // line 11 and 12 will be run before each Test
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
    void willThrowExceptionWhenEmailIsTaken() {

        //given
        Student student = new Student(
                "Tiani", "tiani@parcaune.com", LocalDate.of(2017, Month.JUNE, 21), 4, "345678987654", "assets/images/pic3.jpeg", "456", "student"
        );

        // Mock the behaviour of the StudentRepository
        given(studentRepository.selectExistsEmail(student.getEmail())) // lorsque on fais studentRepository.selectExistsEmail et on lui donne exactement le meme mail
                .willReturn(true);  // sa doi returner True


        //Then
        assertThatThrownBy(() -> underTest.addNewStudent(student))// assure toi que quand on lance lexception,le service.addNewStudent qui prents student
                .isInstanceOf(StudentAppBadRequestException.class) // a une exception de type StudentAppBadRequestException
                .hasMessageContaining("Email " + student.getEmail() + " taken");//et contient le message en parenthese

        verify(studentRepository,never()).save(any()); // mck here dois not saves any stuent(in the bStudentService class it is never executed
    }


    void canAddNewStudent() {

        //given

        Student student = new Student(
                "Tiani", "tiani@parcaune.com", LocalDate.of(2017, Month.JUNE, 21), 4, "345678987654", "assets/images/pic3.jpeg", "456", "student"
        );


        //When

        underTest.addNewStudent(student);

        //Then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        //studentArgumentCaptor.getValue();
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);


    }

    @Test
    @Disabled
        // disables the test
    void deleteStudent() {
    }
}
