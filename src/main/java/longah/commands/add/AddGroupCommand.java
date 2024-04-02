package longah.commands.add;

import longah.commands.Command;
import longah.node.Group;
import longah.exception.LongAhException;
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
     * @param group The group current group.
     */
    public void execute(Group group) throws LongAhException {
        Group newGroup = new Group(this.taskExpression);
        GroupList.addGroup(newGroup);
    }
}
