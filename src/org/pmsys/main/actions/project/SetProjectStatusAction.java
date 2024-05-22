package org.pmsys.main.actions.project;

import org.pmsys.main.entities.Project;
import org.pmsys.main.managers.FormManager;
import org.pmsys.main.managers.ProjectManager;
import org.pmsys.main.ui.CComponent;
import org.pmsys.main.ui.utils.PopupMessages;

import javax.swing.*;

public class SetProjectStatusAction extends AbstractProjectAction{
    @Override
    public void execute(JComponent source, CComponent comp) {
        JMenuItem item = (JMenuItem) source;
        String itemName = item.getText();
        Project currentProject = getCurrentProject();
        if(currentProject.getStatus().equalsIgnoreCase(itemName)) {
            PopupMessages.ERROR("Project already in this status");
            return;
        }

        currentProject.setStatus(itemName);
        projectService.updateProjectInFile(currentProject);
        ProjectManager.INSTANCE.loadProjectList();
        PopupMessages.SUCCESS("Project status updated");
    }
}
