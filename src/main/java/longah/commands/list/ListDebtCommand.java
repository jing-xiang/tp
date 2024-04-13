package longah.commands.list;

import longah.commands.Command;
import longah.node.Group;
import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;
import longah.handler.UI;

public class ListDebtCommand extends Command {
    /**
     * Constructor for ListDebtCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public ListDebtCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the list debt command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        if (!this.taskExpression.isEmpty()) {
            throw new LongAhException(ExceptionMessage.INVALID_LIST_COMMAND);
        }
        UI.showMessage(group.listDebts());
    }
}
