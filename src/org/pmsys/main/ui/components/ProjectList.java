package org.pmsys.main.ui.components;

import org.pmsys.main.ui.components.base.FlatPanel;

import java.util.ArrayList;
import java.util.List;


public class ProjectList extends FlatPanel {

    private static final int MAX_ITEMS = 5;

    private final List<ProjectCard> projectCards = new ArrayList<>();
    private String rowConstraint;
    private int itemCount;

    public ProjectList() {
        initList();
    }

    public void addProject(ProjectCard card) {
        add(card, "h 20%, growx, ");
        projectCards.add(card);
        updateList();
    }

    public void removeAllProjects() {
        projectCards.clear();
        initList();
        revalidate();
        repaint();
    }

    private void updateRowConstraint() {
        rowConstraint += "0[]";
        setRowConstraints(rowConstraint);
    }

    public boolean isFull() {
        return itemCount == MAX_ITEMS;
    }


    private void initList() {
        setConstraints("insets 0, wrap, fillx", "[]", rowConstraint);
        removeAll();
        rowConstraint = "";
        itemCount = 0;
    }

    private void updateList() {
        itemCount++;
        updateRowConstraint();
        repaint();
        revalidate();
    }
}
