package cz.stepit.student.format;

import org.springframework.stereotype.Component;

import cz.stepit.student.entity.Student;
import cz.stepit.student.entity.Subject;

import java.util.stream.Collectors;

@Component
public class StudentSimpleFormatter implements ModelFormatter<Student> {

    @Override
    public String format(Student student) {
        final var string = new StringBuilder("Student ").append(student.getId()).append("\n");
        string.append(formatName(student)).append("\n");
        string.append(formatSubjects(student)).append("\n");
        string.append(formatAverages(student)).append("\n");
        return string.toString();
    }

    protected String formatName(Student student) {
        return " - Name: " + student.getFirstName() + " " + student.getLastName();
    }

    protected String formatSubjects(Student student) {
        final var subjects = student.getSubjects().stream().map(Subject::getName).collect(Collectors.joining(", "));
        return " - Subjects: " + subjects;
    }

    protected String formatAverages(Student student) {
        final var string = new StringBuilder(" - Averages: ").append("\n");
        for (var average : student.getAverages().entrySet()) {
            string.append("\t").append(average.getKey()).append(" -> ").append(average.getValue()).append("\n");
        }
        return string.toString();
    }
}
