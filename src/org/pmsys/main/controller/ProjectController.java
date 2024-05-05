package org.pmsys.main.controller;

import org.pmsys.main.entity.Project;
import org.pmsys.main.entity.ProjectCreateRequest;
import org.pmsys.main.service.ProjectService;
import org.pmsys.main.ui.components.ProjectCardUI;
import org.pmsys.main.ui.views.ProjectListView;
import org.pmsys.main.ui.windows.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * <h1>ProjectController</h1>
 * This class is responsible for coordinating interactions
 * between the ProjectListView and ProjectService.
 * It handles user actions such as:
 * <li>Loading projects</li>
 * <li>Navigation</li>
 * <li>Sorting</li>
 * <li>Exporting</li>
 * <li>Creating new projects</li>
 */
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectListView projectListView;
    private final MainWindow mainWindow;

    /**
     * Constructor for the ProjectController.
     *
     * @param projectService  The service responsible for interacting with project data.
     * @param projectListView The main project list view .
     * @param mainWindow      The main window of the application.
     */
    public ProjectController(ProjectService projectService, ProjectListView projectListView, MainWindow mainWindow) {
        this.projectService = projectService;
        this.projectListView = projectListView;
        this.mainWindow = mainWindow;

        attachListeners();
    }


    /**
     * Loads projects from a file and displays them in the UI.
     */
    public void loadProjectFromFile() {
        for (Project project : projectService.getProjectsFromFile()) {
            displayProjectInUI(project);
        }
    }


    /**
     * Initializes event listeners for various UI components.
     */
    private void attachListeners() {
        projectListView.addNextButtonListener(this::handleNextButtonClick);
        projectListView.addPreviousButtonListener(this::handlePreviousButtonClick);
        projectListView.addExportButtonListener(this::handleExportButtonClick);
        projectListView.addSortButtonListener(this::handleSortButtonClick);
        projectListView.addAddProjectButtonListener(this::handleAddProjectButtonClick);
        projectListView.addProjectCreationListener(this::handleProjectCreationEvent);
    }


    /**
     * Handles the "Next" button click event in the project list view.
     *
     * @param e The ActionEvent triggered by the button click.
     */
    private void handleNextButtonClick(ActionEvent e) {
        if (projectListView.hasNextPage()) {
            projectListView.showNextPage();
            projectListView.updateButtonState();
        }
    }


    /**
     * Handles the "Previous" button click event in the project list view.
     *
     * @param e The ActionEvent triggered by the button click.
     */
    private void handlePreviousButtonClick(ActionEvent e) {
        if (projectListView.hasPreviousPage()) {
            projectListView.showPreviousPage();
            projectListView.updateButtonState();
        }
    }


    /**
     * Handles the "Export" button click event (Placeholder for future implementation).
     *
     * @param e The ActionEvent triggered by the button click.
     */
    private void handleExportButtonClick(ActionEvent e) {
        //TODO
    }


    /**
     * Handles the "Sort" button click event (Placeholder for future implementation).
     *
     * @param e The ActionEvent triggered by the button click.
     */
    private void handleSortButtonClick(ActionEvent e) {
        //TODO
    }


    /**
     * Handles the "Add Project" button click event. Displays the project creation form.
     *
     * @param e The ActionEvent triggered by the button click.
     */
    private void handleAddProjectButtonClick(ActionEvent e) {
        projectListView.showProjectCreateForm();
    }


    /**
     * Handles the project creation event. Creates a new project and saves it.
     *
     * @param e The ActionEvent triggered by the creation event.
     */
    private void handleProjectCreationEvent(ActionEvent e) {
        Project newProject = createAndSaveProject(projectListView.getProjectCreateRequest());
        displayProjectInUI(newProject);
    }

    /**
     * Handles the event of a ProjectCardUI being clicked and a project is opened.
     *
     * @param project The project that was opened.
     */
    private void handleProjectOpenEvent(Project project) {
        mainWindow.getContentLayout().show(mainWindow.getMainContentArea(), "projectView");
    }

    /**
     * Displays a project into the UI view.
     *
     * @param project The project to be displayed.
     */
    private void displayProjectInUI(Project project) {
        projectListView.addProjectToList(createProjectCard(project));
        projectListView.updateButtonState();
    }

    /**
     * Creates a ProjectCardUI for the given project.
     *
     * Will be called by the @displayProjectInUI method.
     *
     * @param project The project for which a card is to be created.
     * @return The created ProjectCardUI.
     */
    private ProjectCardUI createProjectCard(Project project) {
        ProjectCardUI projectCard = new ProjectCardUI(project);
        projectCard.handleProjectOpen(this::handleProjectOpenEvent);
        return projectCard;
    }

    /**
     * Creates a new project and saves it to file.
     *
     * @param request The ProjectCreateRequest containing project details.
     * @return The newly created Project object.
     */
    private Project createAndSaveProject(ProjectCreateRequest request) {
        Project project = new Project(request);
        projectService.saveProjectToFile(project);
        return project;
    }
}
