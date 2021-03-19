package com.parcaune.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository //  b/c interface responsible for Data Access     //waits for the types in <>
public interface StudentRepository extends JpaRepository<Student,Long>{
}
