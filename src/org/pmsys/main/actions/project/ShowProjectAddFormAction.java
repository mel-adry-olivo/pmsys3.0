package org.pmsys.main.actions.project;

import org.pmsys.main.managers.FormManager;
import org.pmsys.main.ui.forms.FormType;
import org.pmsys.main.ui.CComponent;

import javax.swing.*;

public class ShowProjectAddFormAction extends AbstractProjectAction {
    @Override
    public void execute(JComponent source, CComponent comp) {
        FormManager.INSTANCE.showForm(FormType.PROJECT);
    }
}
