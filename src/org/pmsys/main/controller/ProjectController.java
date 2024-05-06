package org.pmsys.main.controller;

import org.pmsys.main.entity.Task;
import org.pmsys.main.service.ProjectService;
import org.pmsys.main.ui.components.TaskCardUI;
import org.pmsys.main.ui.views.ProjectView;

import java.awt.event.ActionEvent;
import java.util.Random;

public class ProjectController{

    private ProjectView projectView;
    private ProjectService projectService;

    public ProjectController(ProjectView projectView, ProjectService projectService) {
        this.projectView = projectView;
        this.projectService = projectService;
        attachListeners();
    }

    public void attachListeners() {
        projectView.addAddTaskButtonListener(this::handleAddTaskClick);
    }

    private void handleAddTaskClick(ActionEvent e) {
        projectView.addTaskToSection(randomTaskCard());
    }

    private TaskCardUI randomTaskCard() {
        return new TaskCardUI(new Task("Title", "Description", "Due Date", "Priority", randomStatusGenerator()));
    }

    private String randomStatusGenerator() {
        Random random = new Random();
        return switch (random.nextInt(4)) {
            case 0 -> "Ready";
            case 1 -> "In Progress";
            case 2 -> "To Review";
            case 3 -> "Done";
            default -> null;
        };
    }
}
