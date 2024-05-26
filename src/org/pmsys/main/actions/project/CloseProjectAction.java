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
        Views previousView = ViewManager.INSTANCE.getPreviousView();
        ViewManager.INSTANCE.showView(previousView);
        ProjectManager.INSTANCE.loadProjectList();
        projectView.resetProjectView();
    }
}
