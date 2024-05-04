package org.pmsys.main.service;

import org.pmsys.main.entity.Project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Manages the persistence of projects, including saving them to the user's file
 * and loading them from it.
 */
public class ProjectService extends FileService {

    /**
     * Saves a project to the current user's associated file.
     *
     * @param project The project to be saved.
     * @throws IOException If an error occurs while saving the project data.
     */
    public void saveProjectToFile(Project project) {
        try ( BufferedWriter writer = createWriterFor( getFileOfUser() ) ) {
            writer.write(project.toString());
            writer.newLine();
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    /**
     * Retrieves a list of projects from the current user's associated file.
     *
     * @return A list of projects. If an error occurs, an empty list is returned.
     */
    public List<Project> getProjectsFromFile() {
        try (BufferedReader reader = createReaderFor( getFileOfUser() )) {
            return reader.lines()
                    .map(Project::parseLineToProject)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
            return Collections.emptyList();
        }
    }
}
