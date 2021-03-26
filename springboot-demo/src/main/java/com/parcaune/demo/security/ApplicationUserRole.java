package com.parcaune.demo.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.parcaune.demo.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ,STUDENT_WRITE,COURSE_WRITE,COURSE_READ)),
    ADMINTRAINEE(Sets.newHashSet(STUDENT_READ,COURSE_READ));

    private final Set<ApplicationUserPermission> Permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        Permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return Permissions;
    }
}
