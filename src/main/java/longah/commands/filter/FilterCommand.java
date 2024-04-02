package longah.commands.filter;

import longah.commands.Command;
import longah.node.Group;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

public class FilterCommand extends Command {
    private String subCommand;

    /**
     * Constructor for FilterCommand.
     *
     * @param commandString The command string.
     * @param taskExpression The task expression.
     * @throws LongAhException If the filter command is invalid.
     */
    public FilterCommand(String commandString, String taskExpression) throws LongAhException {
        super(commandString, taskExpression);
        String[] subCommandTaskExpSplit = this.taskExpression.split(" ", 2);
        this.subCommand = subCommandTaskExpSplit[0].toLowerCase();
        if (subCommandTaskExpSplit.length > 1) {
            this.taskExpression = subCommandTaskExpSplit[1];
        } else {
            throw new LongAhException(ExceptionMessage.INVALID_FILTER_COMMAND);
        }
    }

    public void execute(Group group) throws LongAhException {
        String fullCommandString = this.commandString + " " + this.subCommand;
        switch (this.subCommand) {
            case "datetime":
                FilterDateTimeCommand filterDateTimeCommand =
                        new FilterDateTimeCommand(fullCommandString, this.taskExpression);
                filterDateTimeCommand.execute(group);
                break;
            default:
                throw new LongAhException(ExceptionMessage.INVALID_FILTER_COMMAND);
        }
    }
}

