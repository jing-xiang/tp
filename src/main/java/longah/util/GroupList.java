package longah.util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;
import longah.handler.StorageHandler;
import longah.handler.UI;
import longah.node.Group;

/**
 * Represents a list of groups in the LongAh application.
 */
public class GroupList {
    private static final String GROUP_LIST_FILE_PATH = "./data/groupList.txt";
    private static Group activeGroup;
    private static ArrayList<Group> groupList = new ArrayList<>();

    /**
     * Constructor for GroupList.
     *
     * @throws LongAhException If an I/O exception occurs.
     */
    public GroupList() throws LongAhException {
        StorageHandler.initDir();
        if (!Files.exists(Paths.get(GROUP_LIST_FILE_PATH)) || groupList == null) {
            createGroupList();
        } else {
            loadGroupList();
            getGroupList();
            UI.showMessage("Defaulting to the first group. You are now managing: "
                    + groupList.get(0).getGroupName());
            activeGroup = groupList.get(0);
        }
    }

    /**
     * Returns the active group.
     *
     * @return The active group.
     */
    public static Group getActiveGroup() {
        return activeGroup;
    }

    /**
     * Switches the active group to the specified group.
     *
     * @param newGroup The new group to switch to.
     */
    public static void switchActiveGroup(Group newGroup) {
        activeGroup = newGroup;
    }

    /**
     * Creates a new group list.
     * @throws LongAhException If an I/O exception occurs.
     */
    public static void createGroupList() throws LongAhException {
        groupList = new ArrayList<>();
        UI.showMessage("No groups found. Please give a name for your first group.");
        String groupName = UI.getUserInput();
        Group newGroup = new Group(groupName);
        groupList.add(newGroup);
        try {
            Files.write(Paths.get(GROUP_LIST_FILE_PATH), groupName.getBytes());
        } catch (IOException e) {
            throw new LongAhException(ExceptionMessage.IO_EXCEPTION);
        }
        activeGroup = newGroup;
    }

    /**
     * Loads the group list from the file.
     * @throws LongAhException If an I/O exception occurs.
     */
    public static void loadGroupList() throws LongAhException {
        try {
            String[] data = new String(Files.readAllBytes(Paths.get(GROUP_LIST_FILE_PATH))).split("\n");
            System.out.println(data.length);
            if (data.length == 0) {
                throw new LongAhException(ExceptionMessage.EMPTY_GROUP_LIST);
            }
            for (String groupName : data) {
                Group newGroup = new Group(groupName);
                groupList.add(newGroup);
            }
        } catch (IOException e) {
            throw new LongAhException(ExceptionMessage.IO_EXCEPTION);
        }
    }

    /**
     * Returns the group list.
     *
     * @return The group list.
     * @throws LongAhException If an I/O exception occurs.
     */
    public static String getGroupList() throws LongAhException {
        if (groupList.isEmpty()) {
            throw new LongAhException(ExceptionMessage.EMPTY_GROUP_LIST);
        }
        try {
            String[] data = new String(Files.readAllBytes(Paths.get(GROUP_LIST_FILE_PATH))).split("\n");
            String groupListString = "";
            for (String groupName : data) {
                groupListString += groupName + "\n";
            }
            return groupListString;
        } catch (IOException e) {
            throw new LongAhException(ExceptionMessage.IO_EXCEPTION);
        }
    }

    /**
     * Adds a group to the group list and saves to the txt file.
     *
     * @param group The group to add.
     * @throws LongAhException If the group is already in the list.
     */
    public static void addGroup(Group group) throws LongAhException {
        if (isGroup(group.getGroupName())) {
            throw new LongAhException(ExceptionMessage.DUPLICATE_GROUP);
        }
        groupList.add(group);
        saveGroupList();
    }

    /**
     * Deletes a group from the group list and saves to the txt file.
     *
     * @param group The group to delete.
     * @throws LongAhException If the group is not found.
     */
    public static void deleteGroup(Group group) throws LongAhException {
        if (!GroupList.isGroup(group.getGroupName())) {
            throw new LongAhException(ExceptionMessage.GROUP_NOT_FOUND);
        }
        groupList.remove(group);
        saveGroupList();
    }


    /**
     * Returns the group at the specified index.
     * @param index The index of the group.
     * @return The group at the specified index.
     */
    public Group getGroup(int index) {
        return groupList.get(index);
    }

    /**
     * Returns the size of the group list.
     * @return The size of the group list.
     */
    public int getSize() {
        return groupList.size();
    }

    /**
     * Checks if the group is in the list.
     * @param groupName The name of the group.
     * @return True if the group is in the list, false otherwise.
     */
    public static boolean isGroup(String groupName) {
        for (Group group : groupList) {
            if (group.getGroupName().equals(groupName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the group list to the txt file.
     * @throws LongAhException If an I/O exception occurs.
     */
    public static void saveGroupList() throws LongAhException {
        try {
            FileWriter writer = new FileWriter(GROUP_LIST_FILE_PATH);
            for (Group group : groupList) {
                writer.write(group.getGroupName() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new LongAhException(ExceptionMessage.IO_EXCEPTION);
        }
    }
}
