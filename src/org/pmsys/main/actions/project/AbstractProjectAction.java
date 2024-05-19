package org.pmsys.main.actions.project;

import org.pmsys.main.actions.SimpleAction;
import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.request.ProjectRequestStatus;
import org.pmsys.main.entities.result.ProjectResult;
import org.pmsys.main.managers.FormManager;
import org.pmsys.main.managers.ServiceManager;
import org.pmsys.main.managers.ViewManager;
import org.pmsys.main.service.ProjectService;
import org.pmsys.main.service.Services;
import org.pmsys.main.service.TaskService;
import org.pmsys.main.ui.forms.FormType;
import org.pmsys.main.ui.forms.ProjectSimpleForm;
import org.pmsys.main.ui.views.ProjectListView;
import org.pmsys.main.ui.views.ProjectView;
import org.pmsys.main.ui.views.UIView;
import org.pmsys.main.ui.views.Views;

import javax.swing.*;

public abstract class AbstractProjectAction implements SimpleAction {

    protected final ProjectService projectService;
    protected final TaskService taskService;
    protected final ProjectView projectView;
    protected final ProjectListView projectListView;
    protected final ProjectSimpleForm projectForm;

    public AbstractProjectAction() {
        this.projectService = (ProjectService) ServiceManager.getService(Services.PROJECT);
        this.taskService = (TaskService) ServiceManager.getService(Services.TASK);
        this.projectView = (ProjectView) ViewManager.INSTANCE.getViewComponent(Views.PROJECT);
        this.projectListView = (ProjectListView) ViewManager.INSTANCE.getViewComponent(Views.PROJECT_LIST);
        this.projectForm = (ProjectSimpleForm) FormManager.INSTANCE.getForm(FormType.PROJECT);
    }

    public abstract void execute(JComponent source, UIView view);

    protected Project getCurrentProject() {
        return projectView.getCurrentProject();
    }

    protected void handleProjectFormError(ProjectResult result) {
        ProjectSimpleForm projectForm = (ProjectSimpleForm)FormManager.INSTANCE.getForm(FormType.PROJECT);
        projectForm.showErrorMessage(result.getErrorMessage());
        ProjectRequestStatus status = (ProjectRequestStatus) result.getStatus();
        switch (status) {
            case BLANK_FIELDS:
                projectForm.showErrorFields();
                break;
            case INVALID_DATE:
                projectForm.showInvalidDateError();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + result.getStatus());
        }
    }
}
