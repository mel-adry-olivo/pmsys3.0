package org.pmsys.main.ui.components;

import org.pmsys.main.ui.components.base.FlatScrollPane;

import java.awt.*;

public class TaskBoard extends FlatScrollPane {

    private static final String[] STATUS = {"Ready", "In Progress", "To Review", "Done"};

    private int maxHeight;

    private TaskSectionUI ready;
    private TaskSectionUI inProgress;
    private TaskSectionUI toReview;
    private TaskSectionUI done;

    public TaskBoard() {
        super("insets 16 24 0 24, fillx, wrap 4", "[fill]16[]16[]16[]", "[]16[]");
        setupComponent();

        maxHeight = 0;
    }

    public void addTaskCard(TaskCardUI taskCard) {
         Section section = getSectionByStatus(taskCard.getStatus());
        if (section != null) {
            section.getSection().addTaskCard(taskCard, "w 100%, h 0%, gapbottom 16");
            section.getSection().getParent().doLayout();
            recalculateBoardSize(section);
         }
    }
    private void recalculateBoardSize(Section section) {

        int height = section.getSection().getSize().height;

        if(height > maxHeight) {
            maxHeight = height;
            view.setPreferredSize(new Dimension(view.getWidth(), maxHeight + 74));
        }

        repaint();
        revalidate();
    }

    private TaskSectionUI getSectionByStatus(String status) {
        switch(status) {
            case "Ready" -> {
                return ready;
            }
            case "In Progress" -> {
                return inProgress;
            }
            case "To Review" -> {
                return toReview;
            }
            case "Done" -> {
                return done;
            }
        }
        return null;
    }

    private void setupComponent() {
        setupSectionHeaders();
        setupSections();
    }
    private void setupSectionHeaders() {
        for(String status : STATUS) {
            addToView(new TaskSectionHeaderUI(status), "w 25%, h 0%");
        }
    }
    private void setupSections() {
        ready = new TaskSectionUI(STATUS[0]);
        inProgress = new TaskSectionUI(STATUS[1]);
        toReview = new TaskSectionUI(STATUS[2]);
        done = new TaskSectionUI(STATUS[3]);

        addToView(ready, "w 25%, h 0%, growy");
        addToView(inProgress, "w 25%, h 0%, growy");
        addToView(toReview, "w 25%, h 0%, growy");
        addToView(done, "w 25%, h 0%, growy");
    }
}
