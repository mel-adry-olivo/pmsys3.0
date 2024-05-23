package org.pmsys.main.ui.components;

import org.pmsys.main.ui.components.base.CLabel;
import org.pmsys.main.ui.components.base.CLabelFactory;
import org.pmsys.main.ui.components.base.CPanel;
import org.pmsys.main.ui.components.constants.ColorConstants;

public class ProjectList extends CPanel {

    private final StringBuilder rowConstraint = new StringBuilder();
    private final CLabel emptyLabel;
    private int itemCount;

    public ProjectList() {
        emptyLabel = CLabelFactory.createLargeLabel("No projects found", ColorConstants.DARK_GREY);
        initList();
    }

    public void addProject(ProjectCard card) {
        if (itemCount == 0) {
            remove(emptyLabel);
        }
        add(card, "h 20%, growx");
        itemCount++;
        updateRowConstraint();
        revalidate();
        repaint();
    }

    public void removeAllProjects() {
        initList();
    }

    public boolean isFull() {
        return itemCount >= 5;
    }

    private void initList() {
        removeAll();
        setConstraints("insets 0, wrap, fillx", "[]", "");
        rowConstraint.setLength(0);
        itemCount = 0;
        add(emptyLabel, "al center, h 100%");
        revalidate();
        repaint();
    }

    private void updateRowConstraint() {
        rowConstraint.append("[]");
        setRowConstraints(rowConstraint.toString());
    }
}
