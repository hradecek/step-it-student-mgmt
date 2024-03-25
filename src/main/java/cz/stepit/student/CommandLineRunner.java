package cz.stepit.student;

import org.springframework.stereotype.Component;

import cz.stepit.student.commands.AddGradeCommand;
import cz.stepit.student.commands.Command;
import cz.stepit.student.commands.CreateStudentCommand;
import cz.stepit.student.commands.CreateSubjectCommand;
import cz.stepit.student.commands.ExitCommand;
import cz.stepit.student.commands.ListAllStudentsCommand;
import cz.stepit.student.commands.ListAllSubjectsCommand;
import cz.stepit.student.commands.QueryStudentCommand;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

//@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    protected static final Map<Class<? extends Command>, String> COMMAND_TRANSLATIONS = Map.of(
            CreateSubjectCommand.class, "Create subject",
            ListAllSubjectsCommand.class, "List all subjects",
            CreateStudentCommand.class, "Create student",
            QueryStudentCommand.class, "Query student",
            ListAllStudentsCommand.class, "List all students",
            AddGradeCommand.class, "Add grade",
            ExitCommand.class, "Exit"
    );

    protected final Scanner scanner;
    protected final List<Command> commands;

    public CommandLineRunner(Scanner scanner, List<Command> commands) {
        this.scanner = scanner;
        this.commands = commands;
    }

    @Override
    public void run(String... args) throws Exception {
        printHeader();

        while (true) {
            printOptions();

            final int commandIndex = scanner.nextInt();
            scanner.nextLine();

            System.out.println();
            final var command = getCommand(commandIndex);
            if (command != null) {
                command.run();
            } else {
                System.out.println("Invalid option! Please select a valid option.");
            }
            System.out.println();
            printDivider();
        }
    }

    protected void printHeader() {
        printDivider();
        System.out.println("* Student management system");
        printDivider();
    }

    protected void printOptions() {
        System.out.println("OPTIONS:");
        int commandIndex = 1;
        for (var command : commands) {
            System.out.println("\t" + commandIndex++ + ". " + translateCommand(command));
        }
        System.out.print("Select option: ");
    }

    protected void printDivider() {
        System.out.println("*".repeat(80));
    }

    protected String translateCommand(Command command) {
        return COMMAND_TRANSLATIONS.get(command.getClass());
    }

    protected Command getCommand(int commandIndex) {
        return commands.get(commandIndex - 1);
    }
}
