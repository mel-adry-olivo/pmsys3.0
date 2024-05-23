package org.pmsys.main.managers;

import org.pmsys.main.entities.Project;

import java.util.*;

public enum IndexingManager {

    INSTANCE;

    private final Map<String, Project> titleIndex = new HashMap<>();

    public void indexProject(Project project) {
        titleIndex.put(project.getTitle().toLowerCase(), project);
    }

    public void clearIndex() {
        titleIndex.clear();
    }

    public List<Project> searchProjects(String query) {
        String n = query.toLowerCase();
        List<Project> filteredProjects = new ArrayList<>();

        for (Map.Entry<String, Project> entry : titleIndex.entrySet()) {
            if (entry.getKey().toLowerCase().contains(n)) {
                filteredProjects.add(entry.getValue());
            }
        }

        // sort alphabetically
        filteredProjects.sort(Comparator.comparing(Project::getTitle));

        return filteredProjects;
    }
}
