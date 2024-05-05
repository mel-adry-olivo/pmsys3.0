package org.pmsys.main.ui.components;

import org.pmsys.main.ui.components.base.FlatPanel;

public class TaskSectionUI extends FlatPanel implements Section{

    private String name;

    public TaskSectionUI(String sectionName) {
        name = sectionName;
        setConstraints("insets 0, wrap");
        add(new TaskSectionHeaderUI(sectionName), "w 100%, h 0%");
    }

    public void addTaskCard(TaskCardUI taskCard) {
        add(taskCard, "w 100%, gaptop 8, growx");
    }


    @Override
    public String getSectionName() {
        return name;
    }

}
