package org.pmsys.main.actions.project;

import org.pmsys.main.managers.ProjectManager;
import org.pmsys.main.managers.ViewManager;
import org.pmsys.main.ui.components.base.CComponent;
import org.pmsys.main.ui.views.Views;

import javax.swing.*;

public class CloseProjectAction extends AbstractProjectAction{
    @Override
    public void execute(JComponent source, CComponent comp) {
        taskService.clearCache();
        ViewManager.INSTANCE.showView(Views.PROJECT_LIST);
        ProjectManager.INSTANCE.loadProjectList();
        projectView.resetProjectView();
    }
}
