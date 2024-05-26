package org.pmsys.main.managers;

import org.pmsys.main.entities.Project;
import org.pmsys.main.services.ProjectService;
import org.pmsys.main.services.Services;
import org.pmsys.main.services.TaskService;
import org.pmsys.main.ui.views.DashboardView;
import org.pmsys.main.ui.views.ProjectListView;
import org.pmsys.main.ui.views.ProjectView;
import org.pmsys.main.ui.views.Views;

import java.util.ArrayList;
import java.util.List;

public enum ProjectManager {

    INSTANCE;

    private final ProjectService projectService;
    private final TaskService taskService;
    private final ProjectView projectView;
    private final ProjectListView projectListView;
    private final DashboardView dashboardView;

    ProjectManager() {
        this.projectService = (ProjectService) ServiceManager.getService(Services.PROJECT);
        this.taskService = (TaskService) ServiceManager.getService(Services.TASK);
        this.projectView = (ProjectView) ViewManager.INSTANCE.getViewComponent(Views.PROJECT);
        this.projectListView = (ProjectListView) ViewManager.INSTANCE.getViewComponent(Views.PROJECT_LIST);
        this.dashboardView = (DashboardView) ViewManager.INSTANCE.getViewComponent(Views.DASHBOARD);
    }

    public void clearProjects() {
        IndexingManager.INSTANCE.clearIndex();
        projectListView.resetProjectList();
        projectService.clearCache();
        taskService.clearCache();
        dashboardView.resetDashboard();
    }

    public void loadProjectList() {
        projectListView.resetProjectList();
        IndexingManager.INSTANCE.clearIndex();
        dashboardView.resetDashboard();

        for (Project project : projectService.getCachedProjects()) {
            projectListView.addProjectToUI(projectListView.createProjectCard(project));
            IndexingManager.INSTANCE.indexProject(project);
        }
        projectListView.goToFirstPage();

        loadDashboard();
    }

    private void loadDashboard() {
        int totalProjects = projectService.getCachedProjects().size();
        int doneProjects = 0;
        int overdueProjects = 0;

        List<Project> recentProjects = new ArrayList<>();
        int count = 0;

        for (Project project : projectService.getCachedProjects()) {
            if (project.getStatus().equalsIgnoreCase("done")) {
                doneProjects++;
            } else if (project.getStatus().equalsIgnoreCase("overdue")) {
                overdueProjects++;
            }

            if(count < 4) {
                recentProjects.add(project);
                count++;
            }
        }
        dashboardView.setData(totalProjects, doneProjects, overdueProjects);
        dashboardView.setRecentProjects(recentProjects);
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