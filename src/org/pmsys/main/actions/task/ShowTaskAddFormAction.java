package org.pmsys.main.actions.task;

import org.pmsys.main.managers.FormManager;
import org.pmsys.main.ui.CComponent;
import org.pmsys.main.ui.forms.FormType;

import javax.swing.*;

public class ShowTaskAddFormAction extends AbstractTaskAction{
    @Override
    public void execute(JComponent source, CComponent comp) {
        FormManager.INSTANCE.showForm(FormType.TASK);
    }
}
