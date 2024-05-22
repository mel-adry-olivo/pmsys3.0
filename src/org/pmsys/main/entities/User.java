package org.pmsys.main.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private final String id;
    private final String username;
    private final String hashedPassword;
    private String displayName;

    // Creation
    public User(String username, String hashedPassword) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.hashedPassword = hashedPassword;
    }


    public User(String[] data) {
        this.id = data[0];
        this.username = data[1];
        this.hashedPassword = data[2];
        this.displayName = data[3];
    }

    public static List<User> parseLinesToUsers(List<String> lines) {
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            String[] data = line.split(":::");
            users.add(new User(data));
        }
        return users;
    }

    @Override
    public String toString() {
        return id + ":::" + username + ":::" + hashedPassword +  ":::" + displayName;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
