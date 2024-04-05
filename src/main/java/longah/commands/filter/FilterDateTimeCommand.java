package longah.commands.filter;

import longah.commands.Command;
import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;
import longah.handler.UI;
import longah.node.Group;
import longah.util.TransactionList;

public class FilterDateTimeCommand extends Command {
    /**
     * Constructor for FilterDateTimeCommand.
     *
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public FilterDateTimeCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the corresponding filter datetime command based on the format of the user task input.
     *
     * @param group The group to execute the command on.
     * @throws LongAhException If the taskExpression for the date times to search is in the wrong format
     */
    public void execute(Group group) throws LongAhException {
        TransactionList transactions = group.getTransactionList();
        String message;
        if (!(taskExpression.contains("b/") || taskExpression.contains("a/"))) {
            message = transactions.filterTransactionsEqualToDateTime(taskExpression);
        } else if (taskExpression.contains("a/") && !taskExpression.contains("b/")) {
            message = transactions.filterTransactionsAfterDateTime(taskExpression.replaceAll("a/",""));
        } else if (taskExpression.contains("b/") && !taskExpression.contains("a/")) {
            message =
                    transactions.filterTransactionsBeforeDateTime(taskExpression.replaceAll("b/", ""));
        } else {
            assert taskExpression.contains("a/") && taskExpression.contains("b/") : "Invalid request handled" +
                    "for the filtering between dates";
            String[] splitedExpression = taskExpression.split(" b/");
            if (splitedExpression.length < 2 || !splitedExpression[0].contains("a/")) {
                throw new LongAhException(ExceptionMessage.INVALID_FILTER_DATETIME_COMMAND);
            }
            String fromDateTimeExpression = splitedExpression[0].replaceAll("a/", "");
            String toDateTimeExpression = splitedExpression[1].trim();
            message = transactions.filterTransactionsBetweenDateTime(fromDateTimeExpression, toDateTimeExpression);
        }
        UI.showMessage(message);
    }
}

