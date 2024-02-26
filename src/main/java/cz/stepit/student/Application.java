package cz.stepit.student;

import cz.stepit.student.commands.AddGradeCommand;
import cz.stepit.student.commands.CreateStudentCommand;
import cz.stepit.student.commands.CreateSubjectCommand;
import cz.stepit.student.commands.ExitCommand;
import cz.stepit.student.commands.ListAllStudentsCommand;
import cz.stepit.student.commands.ListAllSubjectsCommand;
import cz.stepit.student.commands.QueryStudentCommand;
import cz.stepit.student.format.StudentSimpleFormatter;
import cz.stepit.student.repository.DBConnectionManager;
import cz.stepit.student.repository.StudentRepository;
import cz.stepit.student.repository.SubjectRepository;

import java.util.List;
import java.util.Scanner;

public class Application {

    private static final String CONNECTION_URL =
            "jdbc:sqlserver://192.168.0.113\\SQLEXPRESS:49875;databaseName=school;username=admin1;password=admin;trustServerCertificate=true;";

    public static void main(String[] args) {
        final var connection = new DBConnectionManager(CONNECTION_URL).getConnection();
        final var studentRepository = new StudentRepository(connection);
        final var subjectRepository = new SubjectRepository(connection);
        final var studentFormatter = new StudentSimpleFormatter();
        final var scanner = new Scanner(System.in);
        final var commands = List.of(
                new CreateSubjectCommand(subjectRepository, scanner),
                new ListAllSubjectsCommand(subjectRepository),
                new CreateStudentCommand(studentRepository, scanner),
                new ListAllStudentsCommand(studentRepository, studentFormatter, scanner),
                new QueryStudentCommand(studentRepository, studentFormatter, scanner),
                new AddGradeCommand(studentRepository, subjectRepository, scanner),
                new ExitCommand()
        );

        new CommandLineRunner(scanner, commands).run();
    }
}
