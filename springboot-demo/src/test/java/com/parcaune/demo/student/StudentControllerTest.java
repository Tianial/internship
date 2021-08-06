package com.parcaune.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StudentController.class) // To test the Controllers, we can use @WebMvcTest. It will auto-configure the Spring MVC infrastructure for our unit tests.
public class StudentControllerTest {

    @Autowired
    private MockMvc mvc;


    @MockBean
    private StudentService service; //We can also use it along with @MockBean to provide mock implementations for any required dependencies.
    //@WebMvcTest also auto-configures MockMvc, which offers a powerful way of easy testing MVC controllers without starting a full HTTP server.


    public void  givenEmployees_whenGetEmployees_thenReturnJsonArray()

            throws Exception{

    }


}
