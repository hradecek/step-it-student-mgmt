package cz.stepit.student.commands;

import org.springframework.stereotype.Component;

/**
 * Ends application.
 */
@Component
public class ExitCommand implements Command {

    @Override
    public void run() {
        System.exit(0);
    }
}
