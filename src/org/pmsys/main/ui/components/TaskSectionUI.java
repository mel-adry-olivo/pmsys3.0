package org.pmsys.main.ui.components;

import org.pmsys.main.ui.components.base.FlatPanel;

import java.awt.*;

public class TaskSectionUI extends FlatPanel implements Section{

    private final String name;
    private String rowConstraint = "";

    public TaskSectionUI(String sectionName) {
        name = sectionName;
        setConstraints("insets 0, flowy", "", rowConstraint);
    }

    public void addTaskCard(TaskCardUI taskCard, String constraint) {
        rowConstraint += "[]0";
        setRowConstraints(rowConstraint);
        add(taskCard, constraint);
    }

    @Override
    public TaskSectionUI getSection() {
        return this;
    }

    public String getSectionName() {
        return name;
    }
}
