package org.pmsys.main.ui.utils;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.entities.Project;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.managers.ViewManager;
import org.pmsys.main.ui.components.ProjectCard;
import org.pmsys.main.ui.views.ProjectListView;
import org.pmsys.main.ui.views.ProjectView;
import org.pmsys.main.ui.views.Views;

public class WarmUp {
    public static void DO_THE_WARMUP() {
        for(int i = 0; i < 10; i++) {
            ActionManager.executeAction(Actions.OPEN_PROJECT, new ProjectCard(Project.EmptyProject()), (ProjectListView)ViewManager.INSTANCE.getViewComponent(Views.PROJECT_LIST));
            ActionManager.executeAction(Actions.CLOSE_PROJECT, null, (ProjectView) ViewManager.INSTANCE.getViewComponent(Views.PROJECT));
        }
    }
}
