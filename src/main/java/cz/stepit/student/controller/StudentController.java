package cz.stepit.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/create")
    public String createStudent() {
        return "createStudent";
    }

    @PostMapping
    public String createStudent(@RequestParam String firstName, @RequestParam String lastName) {
        // TODO: create new student
        return "redirect:/";
    }
}
