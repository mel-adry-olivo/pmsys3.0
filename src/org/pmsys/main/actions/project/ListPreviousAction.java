package org.pmsys.main.actions.project;

import org.pmsys.main.ui.components.base.CComponent;

import javax.swing.*;

public class ListPreviousAction extends AbstractProjectAction {
    @Override
    public void execute(JComponent source, CComponent comp) {
        projectListView.showPreviousPage();
    }
}
