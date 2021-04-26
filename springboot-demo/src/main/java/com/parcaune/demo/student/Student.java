package com.parcaune.demo.student;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

//@Entity also makes sure that the class maps to any DB that will be configued
@Entity  //takes a row in the table of Db and maps or transforms in to the class Student in Java
@Table   // for tables in DB
public class Student implements Serializable {

    @GeneratedValue(strategy=GenerationType.AUTO)     //generates automatically id of type LONG for every saved user
    @Id  // it indicates the attribute id in the DB (le champs sur lequell sa se trouve est ma cle primaire
    private Long id; // permits thanks to the @Id to recognise each of the rows in a unique manner

    private String name;
    private String email;
    private LocalDate dob;
    private Integer age;
    private  String phone;
    private String imageUrl;
    private String studentCode;
    private String studentCategory;

    public Student() {
    }

    public Student(Long id, String name, String email, LocalDate dob, Integer age, String phone, String imageUrl, String studentCode, String studentCategory) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.studentCode = studentCode;
        this.studentCategory = studentCategory;
    }

    public Student(String name, String email, LocalDate dob, Integer age, String phone, String imageUrl, String studentCode, String studentCategory) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.studentCode = studentCode;
        this.studentCategory = studentCategory;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentCategory() {
        return studentCategory;
    }

    public void setStudentCategory(String studentCategory) {
        this.studentCategory = studentCategory;
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

