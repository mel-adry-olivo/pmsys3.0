package org.pmsys.main.actions.project;

import org.pmsys.main.ui.components.base.CComponent;

import javax.swing.*;

public class ListNextAction extends AbstractProjectAction{
    @Override
    public void execute(JComponent source, CComponent comp) {
        projectListView.showNextPage();
    }
}
