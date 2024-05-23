package org.pmsys.main.actions.project;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.ui.components.base.CButton;
import org.pmsys.main.ui.views.ProjectView;
import org.pmsys.main.ui.components.base.CComponent;

import javax.swing.*;

public class ShowProjectOptionsAction extends AbstractProjectAction{
    @Override
    public void execute(JComponent source, CComponent comp) {
        ProjectView.OptionsPopup optionsPopup = new ProjectView.OptionsPopup();
        optionsPopup.handleEditProjectClick(e -> ActionManager.executeAction(Actions.SHOW_PROJECT_EDIT_FORM, source, comp));
        optionsPopup.handleDeleteProjectClick(e -> ActionManager.executeAction(Actions.DELETE_PROJECT, source, comp));
        optionsPopup.handleSetProjectStatusClick(e -> ActionManager.executeAction(Actions.SET_PROJECT_STATUS, source, comp));

        CButton button = (CButton) source;
        int x = button.getWidth() - optionsPopup.getPreferredSize().width;
        optionsPopup.show(button, x, button.getHeight());
    }
}
