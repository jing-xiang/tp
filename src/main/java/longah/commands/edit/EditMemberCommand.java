package longah.commands.edit;

import longah.commands.Command;
import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;
import longah.node.Group;
import longah.util.MemberList;

public class EditMemberCommand extends Command {
    /**
     * Constructor for EditMemberCommand.
     * 
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public EditMemberCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }
    
    /**
     * Executes the edit member name command.
     * 
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        MemberList members = group.getMemberList();
        String[] namesSplit = taskExpression.split("p/", 2);
        if (namesSplit.length != 2) {
            throw new LongAhException(ExceptionMessage.INVALID_EDIT_COMMAND);
        }
        String oldName = namesSplit[0].trim();
        String newName = namesSplit[1].trim();
        members.editMemberName(oldName, newName);
        group.updateTransactionSolution();
        group.saveAllData();
    }
}
