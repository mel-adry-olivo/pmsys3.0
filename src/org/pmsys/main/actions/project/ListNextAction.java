package org.pmsys.main.actions.project;

import org.pmsys.main.ui.CComponent;

import javax.swing.*;

public class ListNextAction extends AbstractProjectAction{
    @Override
    public void execute(JComponent source, CComponent view) {
        projectListView.showNextPage();
    }
}
