package org.pmsys.main.controller;

import org.pmsys.constants.ProjectRequestStatus;
import org.pmsys.constants.Status;
import org.pmsys.main.model.Project;
import org.pmsys.main.model.request.ProjectRequest;
import org.pmsys.main.model.result.ProjectResult;
import org.pmsys.main.service.ProjectService;
import org.pmsys.main.ui.components.ProjectCard;
import org.pmsys.main.ui.utils.Benchmark;
import org.pmsys.main.ui.views.ProjectListView;
import org.pmsys.main.ui.views.ProjectView;

import java.awt.event.ActionEvent;

/**
 * This class is responsible for coordinating interactions
 * between the ProjectListView and ProjectService.
 */
public class ProjectListController{

    private final ProjectService projectService;
    private final ProjectListView projectListView;
    private final ProjectView projectView;

    private final ProjectController projectController;


    public ProjectListController(ProjectService projectService,
                                 ProjectListView projectListView,
                                 ProjectView projectView,
                                 ProjectController projectController) {
        this.projectService = projectService;
        this.projectListView = projectListView;
        this.projectView = projectView;
        this.projectController = projectController;

        projectListView.attachListeners(this);
    }

    public void loadAllProjectsFromFile() {
        projectListView.removeAllProjectCards();
        for (Project project : projectService.getAllProjects().values()) {
            projectListView.addProjectToUI(projectListView.createProjectCard(project, projectController));
        }
        projectListView.goToFirstPage();
    }


    public void handleNextButtonClick(ActionEvent e) {
        if (projectListView.hasNextPage()) {
            projectListView.showNextPage();
            projectListView.updateButtonState();
        }
    }

    public void handlePreviousButtonClick(ActionEvent e) {
        if (projectListView.hasPreviousPage()) {
            projectListView.showPreviousPage();
            projectListView.updateButtonState();
        }
    }

    public void handleExportButtonClick(ActionEvent e) {
        //TODO
    }

    public void handleAddProjectButtonClick(ActionEvent e) {
        projectListView.showProjectForm();
    }

    public void handleProjectCreationEvent(ActionEvent e) {

        ProjectRequest projectRequest = (ProjectRequest) projectListView.getProjectRequest();
        ProjectResult result = projectService.validateProjectRequest(projectRequest);

        if(result.getStatus() != ProjectRequestStatus.SUCCESS) {
            handleProjectCreationError(result);
            return;
        }

        Project validatedProject = result.getProject();
        projectService.saveProject(validatedProject);
        ProjectCard projectCard = projectListView.createProjectCard(validatedProject, projectController);

        projectListView.addProjectToUI(projectCard);
        projectListView.getProjectForm().dispose();
    }


    public void handleProjectCreationError(ProjectResult result) {
        projectListView.getProjectForm().showErrorMessage(result.getErrorMessage());
        ProjectRequestStatus status = (ProjectRequestStatus) result.getStatus();
        switch (status) {
            case BLANK_FIELDS:
                projectListView.getProjectForm().showErrorFields();
                break;
            case INVALID_DATE:
                projectListView.getProjectForm().showInvalidDateError();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + result.getStatus());
        }
    }
}
