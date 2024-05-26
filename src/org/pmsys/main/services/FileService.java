package org.pmsys.main.services;

import org.pmsys.main.entities.User;
import org.pmsys.main.managers.SessionManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;

public abstract class FileService {

    private static final String BASE_DIRECTORY = "data";
    private static final String USER_DATA_DIRECTORY = BASE_DIRECTORY + File.separator + "userdata";
    private static final String PROJECT_DATA_DIRECTORY = BASE_DIRECTORY + File.separator + "projectdata";
    private static final String TASK_DATA_DIRECTORY = BASE_DIRECTORY + File.separator + "taskdata";
    private static final String USER_ACCOUNTS_PATH = USER_DATA_DIRECTORY + File.separator + "useraccounts.txt";
    private static final String REPORT_DIRECTORY = "report";
    private static final String OVERALL_REPORT_DIRECTORY = REPORT_DIRECTORY + File.separator + "overall";
    private static final String INDIVIDUAL_REPORT_DIRECTORY = REPORT_DIRECTORY + File.separator + "individual";

    public FileService() {
        createDirectories(USER_DATA_DIRECTORY, PROJECT_DATA_DIRECTORY, TASK_DATA_DIRECTORY, REPORT_DIRECTORY, OVERALL_REPORT_DIRECTORY, INDIVIDUAL_REPORT_DIRECTORY);
        createFileIfNotExists(USER_ACCOUNTS_PATH);
    }

    protected Path getUserAccountFile() {
        return Paths.get(USER_ACCOUNTS_PATH);
    }

    protected Path getTaskFileOfCurrentUser() {
        return getTaskFileOf(getCurrentUser());
    }

    protected Path getTaskFileOf(User user) {
        return Paths.get(TASK_DATA_DIRECTORY, user.getId() + ".txt");
    }

    protected Path getProjectFileOfCurrentUser() {
        return getProjectFileOf(getCurrentUser());
    }

    protected Path getProjectFileOf(User user) {
        return Paths.get(PROJECT_DATA_DIRECTORY, user.getId() + ".txt");
    }

    protected Path getOverallReportFileOfCurrentUser() {
        return Paths.get(OVERALL_REPORT_DIRECTORY + "/" +"overall-"+ LocalDate.now() + "-" + getCurrentUser().getUsername() + ".txt");
    }

    protected Path getIndividualReportFileOfCurrentUser() {
        return Paths.get(INDIVIDUAL_REPORT_DIRECTORY + "/" +"individiual-"+ LocalDate.now() + "-" + getCurrentUser().getUsername() + ".txt");
    }

    protected User getCurrentUser() {
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
