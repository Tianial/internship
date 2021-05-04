package com.parcaune.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public List<Role> findAllByNameIsIn(List<String> roleNames);

}
