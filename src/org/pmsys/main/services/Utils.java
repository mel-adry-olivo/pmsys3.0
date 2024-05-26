package org.pmsys.main.services;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.logging.Logger;

public class Utils {
    public static void CREATE(Path path) {
        try {
            if(Files.exists(path)) {
                return;
            }

            Files.createFile(path);
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }
    public static void APPEND(Path path, String content) {
        try {
            Files.writeString(
                    path,
                    content + System.lineSeparator(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }
    public static void OVERWRITE(Path path, String content) {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                try {
                    Files.writeString(
                            path,
                            content,
                            StandardOpenOption.WRITE,
                            StandardOpenOption.TRUNCATE_EXISTING);
                } catch (IOException e) {
                    Logger.getGlobal().warning(e.getMessage());
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
    public static List<String> READ(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
            return Collections.emptyList();
        }
    }
    public static <T> String FORMAT(Map<String, T> map) {
        StringBuilder formatter = new StringBuilder();
        for (T t : map.values()) {
            formatter.append(t.toString()).append(System.lineSeparator());
        }
        return formatter.toString();
    }
}
