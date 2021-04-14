package com.parcaune.demo.student;


import javax.persistence.*;
import java.time.LocalDate;

@Entity  //takes a row in the table of Db and maps or transforms in to the class Student in Java
@Table   // for tables in DB
public class Student {

    @GeneratedValue(strategy=GenerationType.AUTO)     //generates automatically id of type LONG for every saved user
    @Id  // it indicates the attribute id in the DB (le champs sur lequell sa se trouve est ma cle primaire
    private Long id; // permits thanks to the @Id to recognise each of the rows in a unique manner

    private String name;
    private String email;
    private LocalDate dob;
    private Integer age;

    public Student() {
    }

    public Student(long id, String name, String email, LocalDate dob, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public Student(String name, String email, LocalDate dob, Integer age) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }


}

