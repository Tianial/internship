package com.parcaune.demo.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

//@Entity also makes sure that the class maps to any DB that will be configued
@Entity  //takes a row in the table of Db and maps or transforms in to the class Student in Java
@Table(name = "STD_MNGMT_USER")
public class User implements Serializable {

    @GeneratedValue(strategy=GenerationType.AUTO)     //generates automatically id of type LONG for every saved user
    @Id  // it indicates the attribute id in the DB (le champs sur lequell sa se trouve est ma cle primaire
    private Long id; // permits thanks to the @Id to recognise each of the rows in a unique manner

    private String username;

    private String password;

    @ManyToMany
    private Set<Role> roles;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonManagedReference
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

