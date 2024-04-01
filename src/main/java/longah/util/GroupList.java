package longah.util;

import java.util.ArrayList;

import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;
import longah.node.Group;

/**
 * Represents a list of groups in the LongAh application.
 */
public class GroupList {
    private static ArrayList<Group> groupList;

    /**
     * Constructs a new GroupList instance with an empty list of groups.
     */
    public GroupList() {
        groupList = new ArrayList<>();
    }

    /**
     * Adds a group to the group list.
     *
     * @param group The group to be added.
     * @throws LongAhException If the group already exists in the list.
     */
    public void addGroup(Group group) throws LongAhException {
        if (groupList.contains(group)) {
            throw new LongAhException(ExceptionMessage.DUPLICATE_GROUP);
        }
        groupList.add(group);
    }

    /**
     * Removes a group from the group list.
     *
     * @param group The group to be removed.
     */
    public void removeGroup(Group group) {
        this.groupList.remove(group);
    }

    /**
     * Returns the list of groups.
     *
     * @return The list of groups.
     */
    public ArrayList<Group> getGroupList() {
        return this.groupList;
    }

    /**
     * Returns the group at the specified index.
     *
     * @param index The index of the group to be returned.
     * @return The group at the specified index.
     */
    public Group getGroup(int index) {
        return this.groupList.get(index);
    }

    /**
     * Returns the size of the group list.
     *
     * @return The size of the group list.
     */
    public int getSize() {
        return groupList.size();
    }

    public static boolean isGroup(String groupName) {
        for (Group group : groupList) {
            if (group.getGroupName().equals(groupName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Lists all the groups in the group list.
     *
     * @return A string representation of all the groups in the group list.
     * @throws LongAhException
     */
    public String listGroups() throws LongAhException {
        if (groupList.isEmpty()) {
            throw new LongAhException(ExceptionMessage.EMPTY_GROUP_LIST);
        }
        String output = "";
        for (Group group : groupList) {
            output += group.toString() + "\n";
        }
        return output;
    }
}
