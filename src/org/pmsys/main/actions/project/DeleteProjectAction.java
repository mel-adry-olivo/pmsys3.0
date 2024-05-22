package org.pmsys.main.actions.project;

import org.pmsys.main.managers.ProjectManager;
import org.pmsys.main.managers.ViewManager;
import org.pmsys.main.ui.components.base.CComponent;
import org.pmsys.main.ui.views.Views;
import org.pmsys.main.ui.utils.MessageUtils;

import javax.swing.*;

public class DeleteProjectAction extends AbstractProjectAction{
    @Override
    public void execute(JComponent source, CComponent comp) {
        if (MessageUtils.CONFIRM_DELETION("project")) {
            projectService.deleteInFile(getCurrentProject());
            ProjectManager.INSTANCE.loadProjectList();
            ViewManager.INSTANCE.showView(Views.PROJECT_LIST);
        }
    }
}
