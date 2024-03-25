package cz.stepit.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cz.stepit.student.entity.Student;
import cz.stepit.student.repository.StudentRepository;

@Controller
@RequestMapping("/student")
public class StudentController {

    protected final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/create")
    public String createStudent() {
        return "createStudent";
    }

    @PostMapping
    public String createStudent(@RequestParam String firstName, @RequestParam String lastName) {
        final var student = new Student(firstName, lastName);
        studentRepository.save(student);

        return "redirect:/";
    }
}
