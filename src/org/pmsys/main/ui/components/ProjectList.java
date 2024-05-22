package org.pmsys.main.ui.components;

import org.pmsys.main.ui.ColorConstants;
import org.pmsys.main.ui.components.base.CLabel;
import org.pmsys.main.ui.components.base.CLabelFactory;
import org.pmsys.main.ui.components.base.CPanel;

import java.util.ArrayList;
import java.util.List;


public class ProjectList extends CPanel {

    private static final int MAX_ITEMS = 5;

    private final List<ProjectCard> projectCards = new ArrayList<>();
    private String rowConstraint;
    private int itemCount;

    private CLabel empty;

    public ProjectList() {
        initList();
        empty = CLabelFactory.createLargeLabel("No projects found", ColorConstants.DARK_GREY);
        add(empty, "al center, h 100%");
    }

    public void addProject(ProjectCard card) {
        if(itemCount == 0) {
            remove(empty);
        }
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
