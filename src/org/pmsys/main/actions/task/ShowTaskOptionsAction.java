package org.pmsys.main.actions.task;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.ui.components.base.CComponent;
import org.pmsys.main.ui.components.TaskCard;
import org.pmsys.main.ui.components.base.CButton;

import javax.swing.*;

public class ShowTaskOptionsAction extends AbstractTaskAction {
    @Override
    public void execute(JComponent source, CComponent comp) {
        TaskCard.OptionsPopup optionsPopup = new TaskCard.OptionsPopup((TaskCard)comp);
        optionsPopup.handleEditProjectClick(e -> ActionManager.executeAction(Actions.SHOW_TASK_EDIT_FORM, source, comp));
        optionsPopup.handleDeleteProjectClick(e -> ActionManager.executeAction(Actions.DELETE_TASK, source, comp));
        optionsPopup.handleSetTaskStatusClick(e -> ActionManager.executeAction(Actions.SET_TASK_STATUS, source, comp));

        CButton button = (CButton) source;
        optionsPopup.show(button, 0, button.getHeight());
    }
}
