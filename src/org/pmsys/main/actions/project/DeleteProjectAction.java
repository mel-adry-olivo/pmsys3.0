package org.pmsys.main.actions.project;

import org.pmsys.main.managers.ProjectManager;
import org.pmsys.main.managers.ViewManager;
import org.pmsys.main.ui.CComponent;
import org.pmsys.main.ui.views.Views;
import org.pmsys.main.utils.PopupMessages;

import javax.swing.*;

public class DeleteProjectAction extends AbstractProjectAction{
    @Override
    public void execute(JComponent source, CComponent view) {
        if (PopupMessages.CONFIRM_DELETION("project")) {
            projectService.deleteProjectFromFile(getCurrentProject());
            ProjectManager.INSTANCE.reloadProjectList();
            ViewManager.INSTANCE.showView(Views.PROJECT_LIST);
        }
    }
}
