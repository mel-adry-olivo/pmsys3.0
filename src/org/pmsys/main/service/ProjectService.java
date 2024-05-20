package org.pmsys.main.service;

import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.request.ProjectRequest;
import org.pmsys.main.entities.result.ProjectResult;
import org.pmsys.main.utils.DateUtils;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProjectService extends FileService {

    private Map<String, Project> projectCache = new LinkedHashMap<>();

    public void cacheProjects() {
        if (projectCache.isEmpty()) {
            projectCache = getAllProjects();
        }
    }

    public void saveProject(Project project) {
        try {
            Files.writeString(
                    getProjectFileOfUser(),
                    project.toString() + System.lineSeparator(),
                    StandardOpenOption.APPEND);
            projectCache.put(project.getId(), project);
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    public void batchSaveTasks() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                try {
                    StringBuilder content = new StringBuilder();
                    for (String projectId : projectCache.keySet()) {
                        content.append(projectCache.get(projectId)).append(System.lineSeparator());
                    }
                    Files.writeString(
                            getProjectFileOfUser(),
                            content.toString(),
                            StandardOpenOption.WRITE,
                            StandardOpenOption.TRUNCATE_EXISTING);
                } catch (IOException e) {
                    Logger.getGlobal().warning(e.getMessage());
                }
                return null;
            }
        }.execute();
    }


    public Map<String, Project> getAllProjects() {
        if(!projectCache.isEmpty()) {
            return projectCache;
        }

        try {
            return Files.readAllLines(getProjectFileOfUser())
                    .stream()
                    .map(Project::parseLineToProject)
                    // used linked hash map to maintain order from file
                    .collect(LinkedHashMap::new, (map, project) -> map.put(project.getId(), project), Map::putAll);

        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
            return Collections.emptyMap();
        }
    }

    public void updateProjectInFile(Project updatedProject){
        projectCache.put(updatedProject.getId(), updatedProject);
        batchSaveTasks();
    }

    public void deleteProjectFromFile(Project project) {
        projectCache.remove(project.getId());
        batchSaveTasks();
    }

    public ProjectResult validateProjectRequest(ProjectRequest projectRequest) {
        if (projectRequest.getTitle().isBlank() || projectRequest.getDescription().isBlank()) {
            return ProjectResult.BLANK_FIELDS();
        }

        LocalDate date = DateUtils.parseFormattedDate(projectRequest.getDueDate());
        if (DateUtils.isPastDate(date)) {
            return ProjectResult.INVALID_DATE();
        }

        return ProjectResult.SUCCESS(new Project(projectRequest));
    }
}
