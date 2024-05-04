package org.pmsys.main.service;

import org.pmsys.main.entity.User;

import java.io.*;

public class UserService extends FileService {

    public void storeUserData(User user) {
        try (BufferedWriter writer = createWriterFor( getFileOfUser(user) );
             BufferedWriter writer2 = createWriterFor( getUserAccountFile() )) {

            writer.write("");
            writer2.write(user.toString());
            writer2.newLine();

        } catch (IOException _) {}
    }

    public User findAccountByUsername(String username) {
        try (BufferedReader reader = createReaderFor( getUserAccountFile() )) {
            return reader.lines()
                    .map(line -> line.split(":::"))
                    .filter(data -> data[1].equals(username))
                    .map(User::new)
                    .findFirst()
                    .orElse(null);

         } catch (IOException _) { return null; }
    }
}
