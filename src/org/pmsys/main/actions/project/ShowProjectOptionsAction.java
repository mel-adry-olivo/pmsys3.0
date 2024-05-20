package org.pmsys.main.actions.project;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.ui.components.base.CButton;
import org.pmsys.main.ui.views.ProjectView;
import org.pmsys.main.ui.CComponent;

import javax.swing.*;

public class ShowProjectOptionsAction extends AbstractProjectAction{
    @Override
    public void execute(JComponent source, CComponent view) {
        ProjectView.OptionsPopup optionsPopup = new ProjectView.OptionsPopup();
        optionsPopup.handleEditProjectClick(e -> ActionManager.executeAction(Actions.SHOW_PROJECT_EDIT_FORM, source, view));
        optionsPopup.handleDeleteProjectClick(e -> ActionManager.executeAction(Actions.DELETE_PROJECT, source, view));


        CButton button = (CButton) source;
        optionsPopup.show(button, 0, button.getHeight());
    }
}
