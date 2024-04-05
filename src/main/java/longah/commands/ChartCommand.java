package longah.commands;

import longah.util.Chart;
import longah.exception.ExceptionMessage;
import longah.handler.Logging;
import longah.util.MemberList;
import longah.node.Member;
import longah.exception.LongAhException;
import longah.node.Group;

import java.util.ArrayList;
import java.util.List;

// @@auther jing-xiang
public class ChartCommand extends Command {
    /**
     * Constructor for ChartCommand.
     *
     * @param commandString The command string.
     * @param taskExpression The task expression.
     */
    public ChartCommand(String commandString, String taskExpression) {
        super(commandString, taskExpression);
    }

    /**
     * Executes the chart command.
     *
     * @param group The group to execute the command on.
     */
    public void execute(Group group) throws LongAhException {
        if (!this.taskExpression.isEmpty()) {
            throw new LongAhException(ExceptionMessage.INVALID_CHART_COMMAND);
        }

        MemberList members = group.getMemberList();
        List<Member> memberList = members.getMembers();
        List<String> memberNames = new ArrayList<>();
        List<Double> memberBalances = new ArrayList<>();
        if (memberList.size() == 0) {
            throw new LongAhException("No members to display");
        }

        for (Member member : memberList) {
            memberNames.add(member.getName());
            memberBalances.add(member.getBalance());
        }

        try {
            Logging.logInfo("Loading balances chart...");
            Chart.viewBalancesBarChart(memberNames, memberBalances);
        } catch (Exception e) {
            throw new LongAhException("Unable to generate chart!");
        }
    }
}
