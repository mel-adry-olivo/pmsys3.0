package org.pmsys.main.managers;

import org.pmsys.main.entities.Project;

import java.util.*;
import java.util.stream.Collectors;

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
        return titleIndex.entrySet().stream()
                .filter(entry -> entry.getKey().contains(n))
                .map(Map.Entry::getValue)
                .sorted(Comparator.comparing(Project::getTitle))
                .collect(Collectors.toList());
    }
}
