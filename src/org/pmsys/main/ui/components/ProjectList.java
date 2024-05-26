package org.pmsys.main.ui.components;

import org.pmsys.main.ui.components.base.CLabel;
import org.pmsys.main.ui.components.base.CLabelFactory;
import org.pmsys.main.ui.components.base.CPanel;
import org.pmsys.main.ui.components.constants.ColorConstants;

public class ProjectList extends CPanel {

    private final StringBuilder rowConstraint = new StringBuilder();
    private final CLabel emptyLabel;
    private int itemCount;
    private final int height;

    public ProjectList(int itemCount) {
        emptyLabel = CLabelFactory.createLargeLabel("No projects found", ColorConstants.DARK_GREY);
        initList();

        height = 100 / itemCount;
    }

    public void addProject(ProjectCard card) {
        if (itemCount == 0) {
            remove(emptyLabel);
        }
        add(card, "h " + height + "%, growx");
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

    public static class Header extends CPanel{

        public Header(int top, int bottom) {
            setConstraints("insets 6px 3% 6px 3%, fill", "", "center")
                    .setMatteBorder(top,0,bottom,0)
                    .applyStyles();

            CLabel nameColumn = CLabelFactory.createSmallLabel("Project Name");
            CLabel descriptionColumn = CLabelFactory.createSmallLabel("Description");
            CLabel taskProgressColumn = CLabelFactory.createSmallLabel("Task Progress");
            CLabel statusColumn = CLabelFactory.createSmallLabel("Status");
            CLabel dueDateColumn = CLabelFactory.createSmallLabel("Due Date");

            add(nameColumn, "w 20%");
            add(descriptionColumn, "w 20%");
            add(taskProgressColumn, "w 20%");
            add(statusColumn, "w 20%");
            add(dueDateColumn, "w 20%");
        }

    }
}
