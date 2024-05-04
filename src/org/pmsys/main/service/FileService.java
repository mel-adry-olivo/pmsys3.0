package org.pmsys.main.service;

import org.pmsys.main.entity.User;
import org.pmsys.main.manager.SessionManager;

import java.io.*;

public abstract class FileService {

    private final String USER_DATA_DIRECTORY = "userdata";
    private final String USER_ACCTS_FILE = USER_DATA_DIRECTORY + File.separator + "useraccounts.txt";

    public FileService(){
        createDirectory(USER_DATA_DIRECTORY);
        createFile(USER_ACCTS_FILE);
    }

    protected BufferedWriter createWriterFor(String filename) throws IOException {
        return new BufferedWriter(new FileWriter(filename, true));
    }
    protected BufferedReader createReaderFor(String filename) throws IOException {
        return new BufferedReader(new FileReader(filename));
    }

    protected String getUserAccountFile() {
        return USER_ACCTS_FILE;
    }

    protected String getFileOfUser() {
        return getFileOf(getCurrentUser());
    }
    protected String getFileOfUser(User user) {
        return getFileOf(user);
    }

    private void createFile(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.out.println("Failed to create file: " + file);
                }
            }
        } catch (IOException e) { throw new RuntimeException(e); }
    }
    private void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                System.out.println("Failed to create directory: " + directory);
            }
        }
    }

    private User getCurrentUser() {
        return SessionManager.getInstance().getCurrentUser();
    }
    private String getFileOf(User user) {
        return USER_DATA_DIRECTORY + File.separator + user.getId() + ".txt";
    }
}
