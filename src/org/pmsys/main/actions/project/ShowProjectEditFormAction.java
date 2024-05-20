package org.pmsys.main.actions.project;

import org.pmsys.main.managers.FormManager;
import org.pmsys.main.ui.forms.FormType;
import org.pmsys.main.ui.CComponent;

import javax.swing.*;

public class ShowProjectEditFormAction extends AbstractProjectAction {
    @Override
    public void execute(JComponent source, CComponent view) {
        FormManager.INSTANCE.showForm(FormType.PROJECT, projectView.getCurrentProject());
    }
}
