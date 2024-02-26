package cz.stepit.student.commands;

import cz.stepit.student.entity.Student;
import cz.stepit.student.format.ModelFormatter;
import cz.stepit.student.repository.StudentRepository;

import java.util.Scanner;

/**
 * Query specific {@link Student}.
 */
public class QueryStudentCommand implements Command {

    protected final StudentRepository studentRepository;
    protected ModelFormatter<Student> studentFormatter;
    protected final Scanner scanner;

    public QueryStudentCommand(StudentRepository studentRepository, ModelFormatter<Student> studentFormatter, Scanner scanner) {
        this.studentRepository = studentRepository;
        this.studentFormatter = studentFormatter;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.print("Enter student ID to query: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        studentRepository.findById(id)
                .ifPresentOrElse(
                        student -> System.out.print(studentFormatter.format(student)),
                        () -> System.out.println("No student found"));
    }
}
