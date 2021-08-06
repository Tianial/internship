package com.parcaune.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository //  b/c interface responsible for Data Access     //waits for the types in <>
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByUsernameEquals(String username);

}
