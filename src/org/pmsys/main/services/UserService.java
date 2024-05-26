package org.pmsys.main.services;

import org.pmsys.main.entities.User;

import java.util.List;

public class UserService extends FileService {

    public void saveInFile(User user) {
        Utils.CREATE(getProjectFileOf(user));
        Utils.CREATE(getTaskFileOf(user));
        Utils.APPEND(getUserAccountFile(), user.toString());
    }

    public List<User> getAllUsers() {
        List<String> lines = Utils.READ(getUserAccountFile());
        return User.parseLinesToUsers(lines);
    }

    public boolean validateUsername(String username) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public User getUserByUsername(String username) {
        List<String> lines = Utils.READ(getUserAccountFile());
        List<User> users = User.parseLinesToUsers(lines);

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
