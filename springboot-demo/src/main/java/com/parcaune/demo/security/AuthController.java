package com.parcaune.demo.security;

import com.parcaune.demo.exceptions.StudentAppRunTimeException;
import com.parcaune.demo.user.Credentials;
import com.parcaune.demo.user.User;
import com.parcaune.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody Credentials credentials) {
        try {
            request.login(credentials.getUsername(), credentials.getPassword());
            User user = userService.getUserByUsername(credentials.getUsername());
            return ResponseEntity.ok(user);
        } catch (ServletException e) {
            throw new StudentAppRunTimeException("Login failed");
        }
    }

    @GetMapping("/logout")
    public void logout() {

        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();

        // will check existence of session. If session exists,
        // then it returns the reference of that session object, if not, this methods will return null.
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                String cookieName = cookie.getName();
                Cookie cookieToDelete = new Cookie(cookieName, null);
                cookieToDelete.setMaxAge(0);
            }
        };
    }

}
