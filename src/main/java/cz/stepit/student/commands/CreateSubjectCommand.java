package cz.stepit.student.commands;

import cz.stepit.student.entity.Subject;
import cz.stepit.student.repository.SubjectRepository;

import java.util.Scanner;

/**
 * Creates new {@link Subject}.
 */
public class CreateSubjectCommand implements Command {

    protected final Scanner scanner;
    protected final SubjectRepository subjectRepository;

    public CreateSubjectCommand(SubjectRepository subjectRepository, Scanner scanner) {
        this.subjectRepository = subjectRepository;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.print("Enter subject name: ");
        final var subjectName = scanner.nextLine();

        final var subject = new Subject(subjectName);

        subjectRepository.create(subject);
    }
}
