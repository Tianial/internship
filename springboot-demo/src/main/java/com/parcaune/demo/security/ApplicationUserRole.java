package com.parcaune.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.parcaune.demo.security.ApplicationUserPermission.*;

// The only difference is that enum constants are public , static and final (unchangeable - cannot be overridden). An enum cannot be used to create objects, and it cannot extend other classes (but it can implement interfaces).

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, COURSE_WRITE, COURSE_READ)),
    ADMINTRAINEE(Sets.newHashSet(STUDENT_READ, COURSE_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissions()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name())); // ⚠️ !!! "ROLE_"

        return permissions;
    }

    /**
     * Return all enum values in a String list
     */
    public static List<String> getValuesAsList = Stream.of(ApplicationUserRole.values())
            .map(Enum::name)
            .collect(Collectors.toList());
}
