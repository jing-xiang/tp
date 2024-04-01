package longah.commands.delete;

import longah.commands.Command;
import longah.exception.LongAhException;
import longah.node.Group;

public class DeleteGroupCommand extends Command {
    /**
     * Constructor for DeleteGroupCommand.
     *
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public DeleteGroupCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the delete group command.
     *
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        //group.deleteGroup();
    }
}
