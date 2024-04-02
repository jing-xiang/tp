package longah;

import longah.util.GroupList;
import longah.handler.Logging;
import longah.handler.PINHandler;
import longah.handler.UI;
import longah.handler.InputHandler;

import longah.exception.LongAhException;
import longah.commands.Command;

/**
 * LongAh class manages debts between members.
 */
public class LongAh {

    public static void init() {
        new Logging();
        UI.showMessage("Welcome to LongAh!");
        UI.showWelcomeMessage();
    }

    public static void loop() throws LongAhException {
        if (GroupList.isEmpty()) {
            GroupList.createGroup();
        } else {
            UI.showCommandPrompt();
            String command = UI.getUserInput();
            Command c = InputHandler.parseInput(command);
            c.execute(GroupList.getActiveGroup());
        }
    }
    /**
     * The main method to run the LongAh application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        init();

        Logging.logInfo("Starting Pre-program preparations.");
        try {
            new PINHandler();
            new GroupList();
        } catch (LongAhException e) {
            LongAhException.printException(e);
        }
        Logging.logInfo("Entering main program body. Begin accepting user commands.");
        while (true) {
            try {
                loop();
            } catch (LongAhException e) {
                LongAhException.printException(e);
            }
        }
    }
}
