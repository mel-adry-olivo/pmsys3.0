package org.pmsys.main.ui.components;

import org.pmsys.main.ui.components.base.FlatPanel;

import javax.swing.*;

public class ProjectListUI extends FlatPanel {

    private static final int MAX_ITEMS = 5;

    private String rowConstraint;
    private int itemCount;

    public ProjectListUI() {
        initList();
    }

    public void addProject(ProjectCardUI card) {
        add(card, "h 20%, growx, ");
        updateList();
    }

    private void updateRowConstraint() {
        rowConstraint = rowConstraint + "0[]";
        setRowConstraints(rowConstraint);
    }

    public boolean isFull() {
        return itemCount == MAX_ITEMS;
    }


    private void initList() {
        setConstraints("insets 0, wrap, fillx", "[]", rowConstraint);
        rowConstraint = "";
        itemCount = 0;
    }

    private void updateList() {
        itemCount++;
        updateRowConstraint();
        revalidate();
    }
}
