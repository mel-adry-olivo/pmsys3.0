package org.pmsys.main.managers;

import org.pmsys.main.entities.Project;
import org.pmsys.main.service.ProjectService;
import org.pmsys.main.service.Services;
import org.pmsys.main.service.TaskService;
import org.pmsys.main.ui.views.ProjectListView;
import org.pmsys.main.ui.views.ProjectView;
import org.pmsys.main.ui.views.Views;

public enum ProjectManager {

    INSTANCE;

    private final ProjectService projectService;
    private final TaskService taskService;
    private final ProjectView projectView;
    private final ProjectListView projectListView;

    ProjectManager() {
        this.projectService = (ProjectService) ServiceManager.getService(Services.PROJECT);
        this.taskService = (TaskService) ServiceManager.getService(Services.TASK);
        this.projectView = (ProjectView) ViewManager.INSTANCE.getViewComponent(Views.PROJECT);
        this.projectListView = (ProjectListView) ViewManager.INSTANCE.getViewComponent(Views.PROJECT_LIST);
    }

    public void reloadProjectList() {
        projectService.cacheProjects();
        projectListView.removeAllProjectCards();
        for (Project project : projectService.getAllProjects().values()) {
            projectListView.addProjectToUI(projectListView.createProjectCard(project));
        }
        projectListView.goToFirstPage();
    }

    public void reloadCurrentProject() {
        Project project = projectView.getCurrentProject();
        projectView.initProjectView(project);

        taskService.cacheTasks();
        project.setTasks(taskService.getTasksOf(project));
        project.getTasks().forEach(taskCard -> projectView.addTaskToView(projectView.createTaskCard(taskCard, null)));
    }

    public void loadProject(Project project) {
        projectView.initProjectView(project);
        taskService.cacheTasks();
        project.setTasks(taskService.getTasksOf(project));
        project.getTasks().forEach(taskCard -> projectView.addTaskToView(projectView.createTaskCard(taskCard, null)));
    }
}