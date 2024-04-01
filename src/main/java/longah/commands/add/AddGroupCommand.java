package longah.commands.add;

import longah.commands.Command;
import longah.node.Group;
import longah.handler.GroupListHandler;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;
import longah.util.GroupList;

public class AddGroupCommand extends Command {
    /**
     * Constructor for AddGroupCommand.
     *
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public AddGroupCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the add group command.
     *
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        String groupName = this.taskExpression;
        if (GroupList.isGroup(groupName)) {
            throw new LongAhException(ExceptionMessage.DUPLICATE_GROUP);
        }
        Group newGroup = new Group(groupName);
        GroupListHandler.addGroup(newGroup);
    }
}
