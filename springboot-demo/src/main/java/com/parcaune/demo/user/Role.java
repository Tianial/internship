package com.parcaune.demo.user;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "STD_MNGMT_ROLE")
public class Role {

    @GeneratedValue(strategy= GenerationType.AUTO)     //generates automatically id of type LONG for every saved user
    @Id  // it indicates the attribute id in the DB (le champs sur lequell sa se trouve est ma cle primaire
    private Long id; // permits thanks to the @Id to recognise each of the rows in a unique manner

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @JsonBackReference
    public Set<User> getUsers() {
        return users;
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

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
