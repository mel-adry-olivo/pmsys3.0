package org.pmsys.main.actions.project;

import org.pmsys.main.entities.Project;
import org.pmsys.main.managers.ProjectManager;
import org.pmsys.main.managers.ViewManager;
import org.pmsys.main.ui.components.ProjectCard;
import org.pmsys.main.ui.views.UIView;
import org.pmsys.main.ui.views.Views;

import javax.swing.*;

public class OpenProjectAction extends AbstractProjectAction {

    @Override
    public void execute(JComponent source, UIView view) {
        ProjectCard projectCard = (ProjectCard) source;
        Project project = projectCard.getProject();
        ProjectManager.INSTANCE.loadProject(project);
        ViewManager.INSTANCE.showView(Views.PROJECT);
    }
}
