package com.parcaune.demo.user;

import com.parcaune.demo.exceptions.StudentAppEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new StudentAppEntityNotFoundException(String.format("User with username [%s] unfortunately not found", username));
        }
        return user;
    }

}
