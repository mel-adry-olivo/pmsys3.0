package org.pmsys.main.actions.project;

import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.request.ProjectRequest;
import org.pmsys.main.entities.request.status.ProjectRequestStatus;
import org.pmsys.main.entities.result.ProjectResult;
import org.pmsys.main.managers.FormManager;
import org.pmsys.main.managers.ProjectManager;
import org.pmsys.main.ui.forms.FormType;
import org.pmsys.main.ui.components.base.CComponent;

import javax.swing.*;

public class EditProjectAction extends AbstractProjectAction{
    @Override
    public void execute(JComponent source, CComponent comp) {
        Project currentProject = getCurrentProject();
        ProjectRequest projectRequest = (ProjectRequest) projectForm.getFormData(currentProject.getId());
        ProjectResult result = validateProjectRequest(projectRequest);

        if(result.getStatus() == ProjectRequestStatus.SUCCESS) {
            Project validatedProject = result.getProject();
            currentProject.updateProject(validatedProject);
            projectService.updateProject(currentProject);

            ProjectManager.INSTANCE.loadProjectList();
            ProjectManager.INSTANCE.reloadCurrentProject();
            FormManager.INSTANCE.hideForm(FormType.PROJECT);
        }
    }

}
