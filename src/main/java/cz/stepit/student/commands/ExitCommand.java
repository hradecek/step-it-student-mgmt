package cz.stepit.student.commands;

/**
 * Ends application.
 */
public class ExitCommand implements Command {

    @Override
    public void run() {
        System.exit(0);
    }
}
