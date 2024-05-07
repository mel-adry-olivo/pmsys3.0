package org.pmsys.main.ui.components;

import org.pmsys.main.entity.Task;
import org.pmsys.main.ui.components.base.FlatPanel;

public interface Section {
    FlatPanel getSection();
    TaskBoardSectionUI.HeaderUI getHeader();
    void addTaskCard(Task task, String constraint);
}
