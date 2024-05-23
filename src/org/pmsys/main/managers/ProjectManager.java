package org.pmsys.main.managers;

import org.pmsys.main.entities.Project;
import org.pmsys.main.services.ProjectService;
import org.pmsys.main.services.Services;
import org.pmsys.main.services.TaskService;
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

    public void clearProjects() {
        IndexingManager.INSTANCE.clearIndex();
        projectListView.resetProjectList();
        projectService.clearCache();
        taskService.clearCache();
    }

    public void loadProjectList() {
        projectListView.resetProjectList();
        IndexingManager.INSTANCE.clearIndex();

        for (Project project : projectService.getCachedProjects()) {
            projectListView.addProjectToUI(projectListView.createProjectCard(project));
            IndexingManager.INSTANCE.indexProject(project);

        }
        projectListView.goToFirstPage();
    }

    public void reloadCurrentProject() {
        loadProject(projectView.getCurrentProject());
    }

    public void loadProject(Project project) {
        projectView.initProjectView(project);
        taskService.cacheTasks();

        project.setTasks(taskService.getTasksOf(project));
        project.getTasks().forEach(task -> projectView.addTaskToView(projectView.createTaskCard(task)));
    }
}