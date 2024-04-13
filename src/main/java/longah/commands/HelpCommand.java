package longah.commands;

import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;
import longah.node.Group;

import longah.handler.UI;

public class HelpCommand extends Command {
    /**
     * Constructor for HelpCommand.
     *
     * @param commandString  The command string.
     * @param taskExpression The task expression.
     */
    public HelpCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }


    /**
     * Executes the help command.
     * @param group The group to execute the command on.
     * @throws LongAhException if unexpected additional parameters are found.
     */
    public void execute(Group group) throws LongAhException {
        if (!this.taskExpression.isEmpty()) {
            throw new LongAhException(ExceptionMessage.INVALID_HELP_COMMAND);
        }
        listAllCommands();
    }

    /**
     * Lists all available commands.
     */
    public static void listAllCommands() {
        UI.showMessage("Here are the full list of commands available:\n");
        UI.showMessage("ADD commands: ");
        UI.printSeparator();
        UI.showMessage("1. `add member [NAME]` - Add a new member to the group.");
        UI.showMessage("2. `add transaction [LENDER] [DD-MM-YYYY HHMM] p/[BORROWER1] a/[AMOUNT OWED]\n" +
                "p/[BORROWER2] a/[AMOUNTED OWED] ...` - Add a new transaction. (date/time inputs are optional)");
        UI.showMessage("3. 'add group [GROUP NAME]' - Add a new group.\n");
        UI.showMessage("LIST commands: ");
        UI.printSeparator();
        UI.showMessage("4. `list members` - List all current members in the group.");
        UI.showMessage("5. `list transactions` - List all transactions in the group.");
        UI.showMessage("6. `list debts` - Simplifies and lists all debts in the group.");
        UI.showMessage("7. `list groups` - List all groups in the application.\n");
        UI.showMessage("DELETE commands: ");
        UI.printSeparator();
        UI.showMessage("8. `delete transaction [TRANSACTION NUMBER]` - Delete a transaction.");
        UI.showMessage("9. `delete member [MEMBER NAME]` - Delete a member from the group.");
        UI.showMessage("10. `delete group [GROUP NAME]` - Delete a group from the application.\n");
        UI.showMessage("FIND commands: ");
        UI.printSeparator();
        UI.showMessage("11. `find borrower [MEMBER NAME]` - Find all transactions where the " +
                "member is a borrower.");
        UI.showMessage("12. `find lender [MEMBER NAME]` - Find all transactions where the member " +
                "is involved as the lender.");
        UI.showMessage("13. `find debts [MEMBER NAME]` - Find all debts of the member.");
        UI.showMessage("14. `find transactions [MEMBER NAME]` - Find all transactions where " +
                "the member is involved.\n");
        UI.showMessage("EDIT commands: ");
        UI.printSeparator();
        UI.showMessage("15. `edit member [MEMBER NAME] p/[NEW MEMBER NAME]` " +
                "- Edit the name of a member.");
        UI.showMessage("16. `edit transaction [TRANSACTION NUMBER] [LENDER] p/[BORROWER1] a/[AMOUNT]\n" +
                "p/[BORROWER2] a/[AMOUNT]...` - Edit the details of a transaction.\n");
        UI.showMessage("PIN commands: ");
        UI.printSeparator();
        UI.showMessage("17. `PIN enable` - Enable PIN authentication for the application.");
        UI.showMessage("18. `PIN disable` - Disable PIN authentication for the application.");
        UI.showMessage("19. `PIN reset` - Reset the user PIN.\n");
        UI.showMessage("OTHER commands: ");
        UI.printSeparator();
        UI.showMessage("20. `settleup [MEMBER NAME]` - Settle all debts of the member.");
        UI.showMessage("21. `clear` - Clear all transaction data in the group.");
        UI.showMessage("22. 'group [GROUP NAME]' - Switch to another group with specified name.");
        UI.showMessage("23. `filter` [TIME PERIOD] - Filter transactions by time period.");
        UI.showMessage("24. `chart` - Display a chart of debts in the group.");
        UI.showMessage("25. `exit` - Exit the application.");
        UI.showMessage("26. `help` - Display the list of commands.\n");
        UI.showMessage("For more information on a specific command, " +
                "or view command shortcuts, do refer to our user guide.");
    }
}
