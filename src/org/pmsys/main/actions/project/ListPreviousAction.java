package org.pmsys.main.actions.project;

import org.pmsys.main.ui.views.UIView;

import javax.swing.*;

public class ListPreviousAction extends AbstractProjectAction {
    @Override
    public void execute(JComponent source, UIView view) {
        projectListView.showPreviousPage();
    }
}
