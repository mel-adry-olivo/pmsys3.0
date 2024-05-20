package org.pmsys.main.service;

import org.pmsys.main.entities.User;
import org.pmsys.main.managers.SessionManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public abstract class FileService {

    private static final String DATA_DIR = "data";
    private static final String USER_DIR = DATA_DIR + File.separator + "userdata";
    private static final String PROJ_DIR = DATA_DIR + File.separator + "projectdata";
    private static final String TASK_DIR = DATA_DIR + File.separator + "taskdata";
    private static final String USER_FILE = USER_DIR + File.separator + "useraccounts.txt";

    public FileService() {
        createDirectories(USER_DIR, PROJ_DIR, TASK_DIR);
        createFileIfNotExists(USER_FILE);
    }

    protected Path getUserAccountFile() {
        return Paths.get(USER_FILE);
    }

    protected Path getTaskFileOfUser() {
        return getTaskFileOf(getCurrentUser());
    }

    protected Path getTaskFileOf(User user) {
        return Paths.get(TASK_DIR, user.getId() + ".txt");
    }

    protected Path getProjectFileOfUser() {
        return getProjectFileOf(getCurrentUser());
    }

    protected Path getProjectFileOf(User user) {
        return Paths.get(PROJ_DIR, user.getId() + ".txt");
    }

    private User getCurrentUser() {
        return SessionManager.INSTANCE.getUser();
    }

    private void createFileIfNotExists(String path) {
        Path filePath = Paths.get(path);
        if (Files.notExists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create file: " + path, e);
            }
        }
    }

    private void createDirectories(String... paths) {
        for (String path : paths) {
            Path dirPath = Paths.get(path);
            if (Files.notExists(dirPath)) {
                try {
                    Files.createDirectories(dirPath);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to create directory: " + path, e);
                }
            }
        }
    }
}
