package org.pmsys.main.actions.project;

import org.pmsys.main.ui.CComponent;

import javax.swing.*;

public class ListPreviousAction extends AbstractProjectAction {
    @Override
    public void execute(JComponent source, CComponent view) {
        projectListView.showPreviousPage();
    }
}
