package com.parcaune.demo.user;

import com.parcaune.demo.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Component
public class UserAndRoleSetupCommandLineRunner implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${student.adminUsername}")
    private String ADMIN_USERNAME;

    @Value("${student.adminPassword}")
    private String ADMIN_PASSWORD;

    @Override
    public void run(String... args) {
        createRoles();
        createAdminUser();
    }

    private void createRoles() {
        System.out.println("Creating Application Roles");

        for (String roleName : ApplicationUserRole.getValuesAsList) {

            boolean exists = roleRepository.existsByNameEquals(roleName);
            if (!exists) {
                Role role = new Role(roleName);
                roleRepository.save(role);
                System.out.printf("Roles [%s] created", roleName);
            } else {
                System.out.printf("Roles [%s] was already created", roleName);
            }

        }
    }

    private void createAdminUser() {
        System.out.println("Creating ADMIN User");
        boolean exists = userRepository.existsByUsernameEquals(ADMIN_USERNAME);
        if (!exists) {
            List<Role> roleList = roleRepository.findAllByNameIsIn(Collections.singletonList(ApplicationUserRole.ADMIN.name())); // ["ADMIN"]

            User adminUser = new User();

            adminUser.setUsername(ADMIN_USERNAME);
            adminUser.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
            adminUser.setRoles(new HashSet<>(roleList));

            userRepository.save(adminUser);
            System.out.println("ADMIN-User created");
        } else {
            System.out.println("ADMIN-User was already created");
        }
    }

}
