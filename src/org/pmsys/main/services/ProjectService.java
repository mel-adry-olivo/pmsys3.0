package org.pmsys.main.services;

import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.request.ProjectRequest;
import org.pmsys.main.entities.result.ProjectResult;
import org.pmsys.main.ui.utils.DateUtils;

import java.time.LocalDate;
import java.util.*;

public class ProjectService extends FileService {

    private Map<String, Project> projectCache = new LinkedHashMap<>();

    public void cacheProjects() {
        if (projectCache.isEmpty()) {
            projectCache = getAllProjects();
        }
    }
    public void clearCache() {
        if(projectCache != null && !projectCache.isEmpty()) {
            projectCache.clear();
        }
    }

    public void saveProject(Project project) {
        projectCache.put(project.getId(), project);
        Utils.APPEND(getProjectFileOfCurrentUser(), project.toString());
    }

    public void updateProject(Project project){
        projectCache.put(project.getId(), project);
        batchSaveProjects();
    }

    public void deleteProject(Project project) {
        projectCache.remove(project.getId());
        batchSaveProjects();
    }

    public Collection<Project> getCachedProjects() {
        cacheProjects();
        return projectCache.values();
    }

    public Project getProjectById(String id) {
        cacheProjects();
        return projectCache.get(id);
    }

    public ProjectResult validateRequest(ProjectRequest projectRequest) {
        if (projectRequest.title().isBlank() || projectRequest.description().isBlank()) {
            return ProjectResult.BLANK_FIELDS();
        }

        LocalDate date = DateUtils.parseFormattedDate(projectRequest.dueDate());
        if (DateUtils.isPastDate(date)) {
            return ProjectResult.INVALID_DATE();
        }

        return ProjectResult.SUCCESS(new Project(projectRequest));
    }

    private Map<String, Project> getAllProjects() {
        List<String> projectLines = Utils.READ(getProjectFileOfCurrentUser());
        return Project.toMap(projectLines);
    }

    private void batchSaveProjects() {
        String formattedProjects = Utils.FORMAT(projectCache);
        Utils.OVERWRITE(getProjectFileOfCurrentUser(), formattedProjects);
    }
}
