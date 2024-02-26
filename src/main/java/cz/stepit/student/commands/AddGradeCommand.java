package cz.stepit.student.commands;

import cz.stepit.student.entity.Grade;
import cz.stepit.student.entity.Student;
import cz.stepit.student.entity.Subject;
import cz.stepit.student.repository.StudentRepository;
import cz.stepit.student.repository.SubjectRepository;

import java.util.Scanner;

/**
 * Adds new grade for specific {@link Student} and {@link Subject}.
 */
public class AddGradeCommand implements Command {

    protected final StudentRepository studentRepository;
    protected final SubjectRepository subjectRepository;
    protected final Scanner scanner;

    public AddGradeCommand(StudentRepository studentRepository, SubjectRepository subjectRepository, Scanner scanner) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.print("Enter student ID: ");
        final var studentId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter subject name: ");
        final var subjectName = scanner.nextLine();

        System.out.print("Enter grade: ");
        final var grade = scanner.nextInt();
        scanner.nextLine();

        final var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student with ID " + studentId + " not found"));
        final var subject = subjectRepository
                .findByName(subjectName)
                .orElseThrow(() -> new RuntimeException("Subject " + subjectName + " not found"));

        student.addGrade(new Grade(subject, grade));

        studentRepository.update(student);
    }
}
