package longah.commands;

import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;
import longah.handler.GroupListHandler;
import longah.handler.UI;
import longah.node.Group;
import longah.util.GroupList;

public class SwitchCommand extends Command {
    /**
     * Constructor for SwitchCommand.
     *
     * @param commandString  The command string.
     * @param taskExpression The task expression.
     */
    public SwitchCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the switch command.
     *
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        if (this.taskExpression.isEmpty()) {
            throw new LongAhException(ExceptionMessage.INVALID_SWITCH_GROUP_COMMAND);
        }
        if (!GroupList.isGroup(this.taskExpression)) {
            throw new LongAhException(ExceptionMessage.GROUP_NOT_FOUND);
        }
        Group newGroup = new Group(this.taskExpression);
        GroupListHandler.switchActiveGroup(newGroup);
        UI.showMessage("Switching to group: " + this.taskExpression);
    }
}
