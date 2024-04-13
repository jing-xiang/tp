package longah.commands;

import longah.handler.UI;
import longah.node.Group;
import longah.util.MemberList;
import longah.util.TransactionList;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

public class ClearCommand extends Command {
    /**
     * Constructor for ClearCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public ClearCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the clear command.
     * 
     * @param group The group to execute the command on.
     * @throws LongAhException If unexpected additional parameters are found.
     */
    public void execute(Group group) throws LongAhException {
        if (!this.taskExpression.isEmpty()) {
            throw new LongAhException(ExceptionMessage.INVALID_CLEAR_COMMAND);
        }
        // Additional message to ask user for confirmation
        UI.showMessage("Are you sure you want to clear all transactions? (Y/N)");
        UI.showMessage("This action cannot be undone. All transaction data will be lost.");
        UI.showMessage("Enter 'N' or any other key to cancel.");
        String confirmation = UI.getUserInput();
        if (confirmation.equalsIgnoreCase("Y")) {
            TransactionList transactions = group.getTransactionList();
            MemberList members = group.getMemberList();
            transactions.clear(members);
            group.updateTransactionSolution();
            group.saveAllData();
            UI.showMessage("All transactions have been cleared for this account.");
        } else {
            UI.showMessage("Clear operation cancelled.");
        }
    }
}
