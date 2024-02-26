package cz.stepit.student.commands;

import cz.stepit.student.entity.Subject;
import cz.stepit.student.repository.SubjectRepository;

import java.util.stream.Collectors;

/**
 * List all {@link Subject}s.
 */
public class ListAllSubjectsCommand implements Command {

    protected final SubjectRepository subjectRepository;

    public ListAllSubjectsCommand(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void run() {
        final var subjects =
                subjectRepository
                        .findAll()
                        .stream()
                        .map(subject -> subject.getId() + " " + subject.getName())
                        .collect(Collectors.joining("\n"));

        System.out.println(subjects);
    }
}
