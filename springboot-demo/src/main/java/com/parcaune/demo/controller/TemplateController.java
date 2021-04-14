
package com.parcaune.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLogin() {
        return "login"; // redirect to templates/login.html
    }

    @GetMapping("courses")
    public String getCourses() {
        return "courses";
    }
}
