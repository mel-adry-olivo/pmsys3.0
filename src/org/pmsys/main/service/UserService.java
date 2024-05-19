package org.pmsys.main.service;

import org.pmsys.main.entities.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

public class UserService extends FileService {

    public void storeUserData(User user) {
        try {
            Files.createFile(getProjectFileOf(user));
            Files.createFile(getTaskFileOf(user));

            Files.writeString(
                    getUserAccountFile(),
                    user.toString() + System.lineSeparator(),
                    StandardOpenOption.APPEND,
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    public User findAccountByUsername(String username) {
        try {
            return Files.readAllLines(getUserAccountFile())
                    .stream()
                    .map(line -> line.split(":::"))
                    .filter(data -> data[1].equals(username))
                    .map(User::new)
                    .findFirst()
                    .orElse(null);
         } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
            return null;
        }
    }
}
