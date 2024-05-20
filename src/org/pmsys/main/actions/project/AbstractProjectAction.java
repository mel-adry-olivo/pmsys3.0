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
import org.pmsys.main.ui.CComponent;
import org.pmsys.main.ui.views.Views;

import javax.swing.*;
import java.util.Objects;

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

    public abstract void execute(JComponent source, CComponent view);

    protected Project getCurrentProject() {
        return projectView.getCurrentProject();
    }

    protected void handleProjectFormError(ProjectResult result) {
        projectForm.showErrorMessage(result.getErrorMessage());
        if (result.getStatus() == ProjectRequestStatus.BLANK_FIELDS) {
            projectForm.showErrorFields();
        } else if (result.getStatus() == ProjectRequestStatus.INVALID_DATE) {
            projectForm.showInvalidDateError();
        } else {
            throw new IllegalStateException("Unexpected value: " + result.getStatus());
        }
    }
}
