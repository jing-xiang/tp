package longah.handler;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import longah.exception.LongAhException;
import longah.node.Group;
import longah.util.GroupList;

/**
 * Handles the creation and loading of the group list.
 */
public class GroupListHandler {
    private static final String GROUP_LIST_FILE_PATH = "./data/groupList.txt";
    private static Group activeGroup;
    private static GroupList groupList = new GroupList();

    /**
     * Constructor for GroupListHandler.
     *
     * @throws LongAhException If there is an error creating the group list.
     */
    public GroupListHandler() throws LongAhException {
        StorageHandler.initDir();
        loadGroupList();
        if (!Files.exists(Paths.get(GROUP_LIST_FILE_PATH)) || groupList == null) {
            createGroupList();
        } else {
            printGroupList();
            UI.showMessage("Defaulting to the first group. You are now managing: "
                    + groupList.getGroup(0).getGroupName());
            activeGroup = groupList.getGroup(0);
        }
    }

    /**
     * Returns the active group.
     * @return The active group.
     */
    public static Group getActiveGroup() {
        return activeGroup;
    }

    /**
     * Switches the active group to the new group.
     *
     * @param newGroup The new group to manage.
     */
    public static void switchActiveGroup(Group newGroup) {
        activeGroup = newGroup;
    }

    /**
     * Creates a new group list with the first group.
     *
     */
    public static void createGroupList() throws LongAhException {
        groupList = new GroupList();
        UI.showMessage("No groups found. Please give a name for your first group.");
        String groupName = UI.getUserInput();
        Group newGroup = new Group(groupName);
        groupList.addGroup(newGroup);
        try {
            Files.write(Paths.get(GROUP_LIST_FILE_PATH), groupName.getBytes());
        } catch (IOException e) {
            UI.showMessage("Error saving group list.");
        }
        activeGroup = newGroup;
    }

    /**
     * Loads the group list from the file.
     *
     * @throws LongAhException If there is an error reading the group list.
     */
    public static void loadGroupList() throws LongAhException {
        try {
            String[] data = new String(Files.readAllBytes(Paths.get(GROUP_LIST_FILE_PATH))).split("\n");
            for (String groupName : data) {
                Group newGroup = new Group(groupName);
                groupList.addGroup(newGroup);
            }
        } catch (IOException e) {
            UI.showMessage("Error reading group list.");
        }
    }

    /**
     * Loads the group list from the file.
     */
    public static void printGroupList() {
        UI.showMessage("Here are the groups you have:");
        try {
            String[] data = new String(Files.readAllBytes(Paths.get(GROUP_LIST_FILE_PATH))).split("\n");
            for (String group : data) {
                UI.showMessage(group);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a group to the group list.
     */
    public static void addGroup (Group newGroup) throws LongAhException {
        groupList.addGroup(newGroup);
        try {
            FileWriter fw = new FileWriter(GROUP_LIST_FILE_PATH, true);
            fw.write("\n" + newGroup.getGroupName());
            fw.close();
        } catch (IOException e) {
            UI.showMessage("Error saving group list.");
        }
    }
}
