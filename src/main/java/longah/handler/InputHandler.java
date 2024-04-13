package longah.handler;

import longah.commands.Command;
import longah.commands.add.AddCommand;
import longah.commands.add.AddGroupCommand;
import longah.commands.add.AddMemberCommand;
import longah.commands.add.AddTransactionCommand;
import longah.commands.delete.DeleteCommand;
import longah.commands.delete.DeleteGroupCommand;
import longah.commands.delete.DeleteMemberCommand;
import longah.commands.delete.DeleteTransactionCommand;
import longah.commands.edit.EditCommand;
import longah.commands.edit.EditMemberCommand;
import longah.commands.edit.EditTransactionCommand;
import longah.commands.filter.FilterCommand;
import longah.commands.find.FindBorrowerCommand;
import longah.commands.find.FindCommand;
import longah.commands.find.FindDebtCommand;
import longah.commands.find.FindLenderCommand;
import longah.commands.find.FindTransactionCommand;
import longah.commands.list.ListCommand;
import longah.commands.list.ListDebtCommand;
import longah.commands.list.ListGroupsCommand;
import longah.commands.list.ListMemberCommand;
import longah.commands.list.ListTransactionCommand;
import longah.commands.ClearCommand;
import longah.commands.SettleCommand;
import longah.commands.ExitCommand;
import longah.commands.PINCommand;
import longah.commands.HelpCommand;
import longah.commands.SwitchCommand;
import longah.commands.ChartCommand;
import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;

public class InputHandler {
    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The user input.
     * @return The corresponding command.
     */
    public static Command parseInput(String userInput) throws LongAhException {
        String[] commandExpressionSplit = userInput.split(" ", 2);
        String commandString = commandExpressionSplit[0].toLowerCase();
        String taskExpression = commandExpressionSplit.length > 1 ? commandExpressionSplit[1] : "";
        return parseCommand(commandString, taskExpression);
    }

    /**
     * Parses the command string and returns the corresponding command.
     *
     * @param commandString The command string.
     * @param taskExpression The task expression.
     * @return The corresponding command of type {@link Command}.
     */
    public static Command parseCommand(String commandString, String taskExpression)
            throws LongAhException {
        switch (commandString) {
        case "add":
            return new AddCommand(commandString, taskExpression);
        case "addt":
            // Fallthrough
        case "at":
            return new AddTransactionCommand("add transaction", taskExpression);
        case "addm":
            // Fallthrough
        case "am":
            return new AddMemberCommand("add member", taskExpression);
        case "addg":
            // Fallthrough
        case "ag":
            return new AddGroupCommand("add group", taskExpression);

        case "list":
            return new ListCommand(commandString, taskExpression);
        case "listt":
            // Fallthrough
        case "lt":
            return new ListTransactionCommand("list transactions", taskExpression);
        case "listm":
            // Fallthrough
        case "lm":
            return new ListMemberCommand("list members", taskExpression);
        case "listd":
            // Fallthrough
        case "ld":
            return new ListDebtCommand("list debts", taskExpression);
        case "listg":
            // Fallthrough
        case "lg":
            return new ListGroupsCommand("list groups", taskExpression);

        case "find":
            return new FindCommand(commandString, taskExpression);
        case "findt":
            // Fallthrough
        case "ft":
            return new FindTransactionCommand("find transactions", taskExpression);
        case "findd":
            // Fallthrough
        case "fd":
            return new FindDebtCommand("find debts", taskExpression);
        case "findl":
            // Fallthrough
        case "fl":
            return new FindLenderCommand("find lender", taskExpression);
        case "findb":
            // Fallthrough
        case "fb":
            return new FindBorrowerCommand("find borrower", taskExpression);
        
        case "filter":
            return new FilterCommand(commandString, taskExpression);
        case "delete":
            return new DeleteCommand(commandString, taskExpression);
        case "deleteg":
            // Fallthrough
        case "dg":
            return new DeleteGroupCommand("delete group", taskExpression);
        case "deletem":
            // Fallthrough
        case "dm":
            return new DeleteMemberCommand("delete member", taskExpression);
        case "deletet":
            // Fallthrough
        case "dt":
            return new DeleteTransactionCommand("delete transaction", taskExpression);

        case "edit":
            return new EditCommand(commandString, taskExpression);
        case "editm":
            // Fallthrough
        case "em":
            return new EditMemberCommand("edit member", taskExpression);
        case "editt":
            // Fallthrough
        case "et":
            return new EditTransactionCommand("edit transaction", taskExpression);
        
        case "?":
            // Fallthrough
        case "help":
            return new HelpCommand(commandString, taskExpression);

        case "settle":
            // Fallthrough
        case "settleup":
            return new SettleCommand(commandString, taskExpression);

        case "clear":
            return new ClearCommand(commandString, taskExpression);
        case "pin":
            return new PINCommand(commandString, taskExpression);
        case "chart":
            return new ChartCommand(commandString, taskExpression);
        case "group":
            return new SwitchCommand(commandString, taskExpression);

        case "close":
            // Fallthrough
        case "exit":
            return new ExitCommand(commandString, taskExpression);

        default:
            throw new LongAhException(ExceptionMessage.INVALID_COMMAND);
        }
    }
}
