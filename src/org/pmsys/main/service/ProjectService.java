package org.pmsys.main.service;

import org.pmsys.main.entity.Project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProjectService extends FileService {

    public void saveProjectToFile(Project project) {
        try ( BufferedWriter writer = createWriterFor( getFileOfUser() ) ) {
            writer.write(project.toString());
            writer.newLine();
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    public List<Project> getProjectsFromFile() {
        try (BufferedReader reader = createReaderFor( getFileOfUser() )) {
            return reader.lines()
                    .map(Project::parseToProject)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
            return Collections.emptyList();
        }
    }
}
