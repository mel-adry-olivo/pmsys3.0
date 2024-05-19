package org.pmsys.main.service;

import org.pmsys.main.model.Project;
import org.pmsys.main.model.Task;
import org.pmsys.main.model.request.ProjectRequest;
import org.pmsys.main.model.result.ProjectResult;
import org.pmsys.main.utils.DateUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProjectService extends FileService {

    private Map<String, Project> projectCache = null;

    public void cacheProjects() {
        if (projectCache == null) {
            projectCache = getAllProjects();
        }
    }

    public void clearCache() {
        projectCache = null;
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

    private void batchSaveTasks() {
        try {
            StringBuilder content = new StringBuilder();
            for (Project project : projectCache.values()) {
                content.append(project.toString()).append(System.lineSeparator());
            }

            Files.writeString(
                    getProjectFileOfUser(),
                    content.toString(),
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    public Map<String, Project> getAllProjects() {
        if(projectCache != null) {
            return projectCache;
        }

        try {
            return Files.readAllLines(getProjectFileOfUser())
                    .stream()
                    .map(Project::parseLineToProject)
                    .collect(Collectors.toMap(Project::getId, project -> project));

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
