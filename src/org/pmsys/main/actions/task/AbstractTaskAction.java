package org.pmsys.main.actions.task;

import org.pmsys.main.actions.SimpleAction;
import org.pmsys.main.entities.request.TaskRequest;
import org.pmsys.main.entities.request.TaskRequestStatus;
import org.pmsys.main.entities.result.TaskResult;
import org.pmsys.main.managers.FormManager;
import org.pmsys.main.managers.ServiceManager;
import org.pmsys.main.managers.ViewManager;
import org.pmsys.main.service.ProjectService;
import org.pmsys.main.service.Services;
import org.pmsys.main.service.TaskService;
import org.pmsys.main.ui.forms.FormType;
import org.pmsys.main.ui.forms.TaskSimpleForm;
import org.pmsys.main.ui.views.ProjectView;
import org.pmsys.main.ui.views.Views;


public abstract class AbstractTaskAction implements SimpleAction {

    protected final ProjectService projectService;
    protected final TaskService taskService;
    protected final ProjectView projectView;
    protected final TaskSimpleForm taskForm;

    public AbstractTaskAction() {
        this.projectService = (ProjectService) ServiceManager.getService(Services.PROJECT);
        this.taskService = (TaskService) ServiceManager.getService(Services.TASK);
        this.projectView = (ProjectView) ViewManager.INSTANCE.getViewComponent(Views.PROJECT);
        this.taskForm = (TaskSimpleForm) FormManager.INSTANCE.getForm(FormType.TASK);
    }

    protected void handleTaskFormError(TaskResult result) {
        taskForm.showErrorMessage(result.getErrorMessage());
        if (result.getStatus() == TaskRequestStatus.BLANK_FIELDS) {
            taskForm.showErrorFields();
        } else {
            throw new IllegalStateException("Unexpected value: " + result.getStatus());
        }
    }

    protected TaskResult validateTaskRequest(TaskRequest taskRequest) {
        TaskResult result = taskService.validateTaskRequest(taskRequest);
        if (result.getStatus() != TaskRequestStatus.SUCCESS) {
            handleTaskFormError(result);
        }
        return result;
    }

    protected void updateProjectAndHideForm() {
        projectService.updateProjectInFile(projectView.getCurrentProject());
        taskForm.dispose();
    }
}
