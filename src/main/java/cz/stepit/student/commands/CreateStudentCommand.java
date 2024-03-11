package cz.stepit.student.commands;

import org.springframework.stereotype.Component;

import cz.stepit.student.entity.Student;
import cz.stepit.student.repository.StudentRepository;

import java.util.Scanner;

/**
 * Creates new {@link Student}.
 *
 * <p>Command prompts user for all necessary information.
 */
@Component
public class CreateStudentCommand implements Command {

    protected final StudentRepository studentRepository;
    protected final Scanner scanner;

    public CreateStudentCommand(StudentRepository studentRepository, Scanner scanner) {
        this.studentRepository = studentRepository;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.print("Enter first name: ");
        final var firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        final var lastName = scanner.nextLine();

        final var student = new Student(firstName, lastName);

        studentRepository.create(student);
    }
}
