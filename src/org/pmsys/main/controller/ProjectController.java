package org.pmsys.main.controller;

import org.pmsys.constants.FormType;
import org.pmsys.constants.ProjectRequestStatus;
import org.pmsys.constants.TaskRequestStatus;
import org.pmsys.constants.View;
import org.pmsys.main.manager.FormManager;
import org.pmsys.main.model.Project;
import org.pmsys.main.model.Task;
import org.pmsys.main.manager.ViewManager;
import org.pmsys.main.model.request.ProjectRequest;
import org.pmsys.main.model.request.TaskRequest;
import org.pmsys.main.model.result.ProjectResult;
import org.pmsys.main.model.result.TaskResult;
import org.pmsys.main.service.ProjectService;
import org.pmsys.main.service.TaskService;
import org.pmsys.main.ui.components.TaskCard;
import org.pmsys.main.ui.components.base.FlatButton;
import org.pmsys.main.ui.listeners.ProjectOpenListener;
import org.pmsys.main.ui.views.ProjectView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Manages interactions between the user interface (ProjectView) and the data/logic layers
 * (ProjectService, TaskService) for project-related operations.
 */
public final class ProjectController implements ProjectOpenListener {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final ProjectView projectView;
    private final FormManager formManager;
    private final ViewManager viewManager;

    private ProjectListController projectListController;

    public ProjectController(ProjectService projectService,
                             TaskService taskService,
                             ProjectView projectView,
                             FormManager formManager,
                             ViewManager viewManager) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.projectView = projectView;
        this.formManager = formManager;
        this.viewManager = viewManager;
        projectView.attachListeners(this);
    }

    @Override
    public void onProjectOpened(Project project) {
        taskService.cacheTasks();
        project.setTasks(taskService.getTasksOf(project));
        displayProjectDetails(project);
        viewManager.showView(View.PROJECT);
    }

    public void setProjectListController(ProjectListController projectListController) {
        this.projectListController = projectListController;
    }

    public void displayProjectDetails(Project project) {
        projectView.updateProjectView(project);
        project.getTasks().forEach(taskCard -> projectView.addTaskToView(projectView.createTaskCard(taskCard, this)));
    }

    // ui events
    public void handleCloseClick(ActionEvent e) {
        taskService.clearCache();
        viewManager.showView(View.PROJECT_LIST);
        projectListController.loadAllProjectsFromFile();
    }

    public void handleExportClick(ActionEvent e) {
        //TODO
    }

    public void handleTaskAddClick(ActionEvent e) {
        formManager.setFormActionHandler(FormType.TASK, this::handleTaskCreationEvent);
        formManager.showForm(FormType.TASK, null);
    }

    public void handleTaskEditClick(TaskCard taskCard) {
        formManager.setFormActionHandler(FormType.TASK, e -> handleTaskEditEvent(taskCard));
        formManager.showForm(FormType.TASK, taskCard.get());
    }
    public void handleTaskDeleteClick(TaskCard taskCard) {
        if (confirmDeletion("task")) {
            deleteTask(taskCard);
        }
    }

    public void handleTaskOptionClick(ActionEvent e, TaskCard taskCard) {
        showTaskOptionsPopup(e, taskCard);
    }

    public void handleProjectOptionsClick(ActionEvent e) {
        showProjectOptionsPopup(e);
    }

    public void handleProjectDeleteClick(ActionEvent e) {
        if(confirmDeletion("project")) {
            deleteProject();
        }
    }

    public void handleProjectEditClick(ActionEvent e) {
        formManager.setFormActionHandler(FormType.PROJECT, this::handleProjectEditEvent);
        formManager.showForm(FormType.PROJECT, projectView.getCurrentProject());
    }

    // edit and creation events
    private void handleTaskEditEvent(TaskCard taskCard) {
        Task taskToEdit = taskCard.get();
        String oldStatus = taskToEdit.getStatus();
        TaskRequest taskRequest = (TaskRequest) formManager.getFormData(FormType.TASK, taskToEdit.getId());
        TaskResult result = taskService.validateTaskRequest(taskRequest);

        if(result.getStatus() != TaskRequestStatus.SUCCESS) {
            handleTaskCreationError(result);
            return;
        }

        Task validatedTask = result.getTask();
        updateTaskInFile(taskToEdit, validatedTask);
        updateTaskInUI(taskToEdit, oldStatus);
        refreshProjectProgress(taskToEdit, "update");
    }

    private void handleTaskCreationEvent(ActionEvent e) {
        TaskRequest taskRequest = (TaskRequest) formManager.getFormData(FormType.TASK);
        TaskResult result = taskService.validateTaskRequest(taskRequest);

        if(result.getStatus() != TaskRequestStatus.SUCCESS) {
            handleTaskCreationError(result);
            return;
        }

        Task validatedTask = result.getTask();
        saveTaskToFile(validatedTask);
        displayTaskInUI(validatedTask);
        refreshProjectProgress(validatedTask, "add");
    }

    private void handleProjectEditEvent(ActionEvent e) {
        Project currentProject = projectView.getCurrentProject();
        ProjectRequest projectRequest = (ProjectRequest) formManager.getFormData(FormType.PROJECT, currentProject.getId());
        ProjectResult result = projectService.validateProjectRequest(projectRequest);

        if(result.getStatus() != ProjectRequestStatus.SUCCESS) {
            projectListController.handleProjectCreationError(result);
            return;
        }

        Project validatedProject = result.getProject();
        updateProjectInFile(currentProject, validatedProject);
        refreshProjectView(currentProject);
    }

    // helper methods
    private boolean confirmDeletion(String itemType) {
        int result = JOptionPane.showConfirmDialog(
                projectView,
                "Are you sure you want to delete this " + itemType + "?",
                "Confirm " + itemType + " Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        return result == JOptionPane.YES_OPTION;
    }

    private void deleteTask(TaskCard taskCard) {
        taskService.deleteTask(taskCard.get());
        projectView.removeTaskFromView(taskCard);
        refreshProjectProgress(taskCard.get(), "delete");
    }

    private void deleteProject() {
        projectService.deleteProjectFromFile(projectView.getCurrentProject());
        projectListController.loadAllProjectsFromFile();
        viewManager.showView(View.PROJECT_LIST);
    }

    private void updateTaskInFile(Task taskToEdit, Task validatedTask) {
        taskToEdit.updateTaskDetails(validatedTask);
        taskService.updateTask(taskToEdit);
    }

    private void updateProjectInFile(Project existingProject, Project updatedProject) {
        existingProject.updateTask(updatedProject);
        projectService.updateProjectInFile(updatedProject);
    }

    private void refreshProjectView(Project project) {
        projectListController.loadAllProjectsFromFile();
        projectView.updateProjectView(project);
        formManager.hideForm(FormType.PROJECT);
    }

    private void refreshProjectProgress(Task task, String action) {
        switch(action) {
            case "add":
                projectView.getCurrentProject().addTask(task);
                break;
            case "delete":
                projectView.getCurrentProject().removeTask(task);
                break;
            case "update":
                projectView.getCurrentProject().revalidateCounts();
                break;
        }
        projectService.updateProjectInFile(projectView.getCurrentProject());
    }

    private void saveTaskToFile(Task task) {
        task.setProjectId(projectView.getCurrentProject().getId());
        taskService.saveTask(task);
    }

    private void displayTaskInUI(Task task) {
        TaskCard taskCard = projectView.createTaskCard(task, this);
        projectView.addTaskToView(taskCard);
        formManager.hideForm(FormType.TASK);
    }

    private void updateTaskInUI(Task task, String oldStatus) {
        TaskCard taskCard = projectView.createTaskCard(task, this);
        projectView.updateTaskInView(taskCard, oldStatus);
        formManager.hideForm(FormType.TASK);
    }

    private void handleTaskCreationError(TaskResult result) {
        formManager.getFormUI(FormType.TASK).showErrorMessage(result.getErrorMessage());
        if (result.getStatus() == TaskRequestStatus.BLANK_FIELDS) {
            formManager.getFormUI(FormType.TASK).showErrorFields();
        }
    }

    private void showTaskOptionsPopup(ActionEvent e, TaskCard taskCard) {
        TaskCard.OptionsPopup optionsPopup = new TaskCard.OptionsPopup(this, taskCard);
        FlatButton button = (FlatButton) e.getSource();
        optionsPopup.show(button, 0, button.getHeight());
    }

    private void showProjectOptionsPopup(ActionEvent e) {
        ProjectView.OptionsPopup optionsPopup = new ProjectView.OptionsPopup(this);
        FlatButton button = (FlatButton) e.getSource();
        optionsPopup.show(button, 0, button.getHeight());
    }
}
