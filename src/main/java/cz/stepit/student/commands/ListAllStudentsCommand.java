package cz.stepit.student.commands;

import org.springframework.stereotype.Component;

import cz.stepit.student.entity.Student;
import cz.stepit.student.format.ModelFormatter;
import cz.stepit.student.repository.StudentRepository;

import java.util.Scanner;

/**
 * List all {@link Student}s.
 */
@Component
public class ListAllStudentsCommand implements Command {

    protected final StudentRepository studentRepository;
    protected final ModelFormatter<Student> studentFormatter;
    protected final Scanner scanner;

    public ListAllStudentsCommand(StudentRepository studentRepository, ModelFormatter<Student> studentFormatter, Scanner scanner) {
        this.studentRepository = studentRepository;
        this.studentFormatter = studentFormatter;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        studentRepository.findAll().stream().map(studentFormatter::format).forEach(System.out::print);
    }
}
