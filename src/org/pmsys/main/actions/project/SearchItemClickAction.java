package org.pmsys.main.actions.project;

import org.pmsys.main.entities.Project;
import org.pmsys.main.managers.ProjectManager;
import org.pmsys.main.managers.ViewManager;
import org.pmsys.main.ui.CComponent;
import org.pmsys.main.ui.components.SearchBar;
import org.pmsys.main.ui.views.Views;

import javax.swing.*;

public class SearchItemClickAction extends AbstractProjectAction {
    @Override
    public void execute(JComponent source, CComponent comp) {
        JMenuItem projectCard = (JMenuItem) source;
        SearchBar searchBar = (SearchBar) comp;
        Project project = projectService.getProjectById(projectCard.getName());
        ProjectManager.INSTANCE.loadProject(project);
        ViewManager.INSTANCE.showView(Views.PROJECT);
        searchBar.setText("");
    }
}
