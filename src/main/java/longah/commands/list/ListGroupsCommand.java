package longah.commands.list;

import longah.commands.Command;
import longah.handler.UI;
import longah.node.Group;
import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;
import longah.util.GroupList;

public class ListGroupsCommand extends Command {
    /**
     * Constructor for ListGroupCommand.
     *
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public ListGroupsCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the list group command.
     *
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        if (!this.taskExpression.isEmpty()) {
            throw new LongAhException(ExceptionMessage.INVALID_LIST_COMMAND);
        }
        String output = GroupList.getGroupList();
        UI.showMessage(output);
    }
}
