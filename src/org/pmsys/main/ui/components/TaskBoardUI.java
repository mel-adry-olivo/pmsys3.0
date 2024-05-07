package org.pmsys.main.ui.components;

import org.pmsys.main.entity.Task;
import org.pmsys.main.ui.components.base.FlatScrollPane;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a visual board for managing tasks within a project.
 * The board is divided into sections based on task status (e.g., Ready, In Progress).
 */
public class TaskBoardUI extends FlatScrollPane {

    private final TaskBoardOptions options;
    private final Map<String, Section> sections;
    private int maxSectionHeight;

    /**
     * Creates a new TaskBoardUI with default configuration.
     */
    public TaskBoardUI() {
        options = new TaskBoardOptions();
        sections = new HashMap<>();
        initBoard();
        setupBoardLayout();
    }

    /**
     * Adds a new section to the task board.
     *
     * @param status  The name/status to identify the section (e.g., "Ready")
     * @param section The Section object representing the visual components of the section
     */
    public void addSection(String status, Section section) {
        sections.put(status, section);
        updateBoardLayout();
        addToView(section.getHeader(), options.getHeaderConstraints());
        addToView(section.getSection(), options.getSectionConstraints());
    }

    /**
     * Adds a single task card to the appropriate section of the task board.
     *
     * @param task The Task object containing the task's details
     */
    public void addTask(Task task) {
        Section section = sections.get(task.getStatus());
        if (section != null) {
            section.addTaskCard(task, options.getCardConstraints());
            section.getSection().getParent().doLayout();
            recalculateBoardSize(section);
         }
    }

    /**
     * Recalculates the size of the board based on the section heights
     * and adjusts the scroll pane dimensions accordingly.
     *
     * @param section The section that caused a potential size change
     */
    private void recalculateBoardSize(Section section) {

        int currentSectionHeight = section.getSection().getSize().height;

        if(currentSectionHeight > maxSectionHeight) {
            maxSectionHeight = currentSectionHeight;
            view.setPreferredSize(new Dimension(view.getWidth(), maxSectionHeight + options.getSectionHeightWithoutHeader()));
        }

        repaint();
        revalidate();
    }

    /**
     * Initializes the task board with default sections (Ready, In Progress, To Review, Done)
     */
    private void initBoard() {
        maxSectionHeight = 0;
        addSection("Ready", new TaskBoardSectionUI("Ready"));
        addSection("In Progress", new TaskBoardSectionUI("In Progress"));
        addSection("To Review", new TaskBoardSectionUI("To Review"));
        addSection("Done", new TaskBoardSectionUI("Done"));

    }

    /**
     * Updates the layout constraints when sections are added/removed.
     */
    private void updateBoardLayout() {
        if (view.getComponentCount() == 0) {
            setColumnConstraints("[]");
            return;
        }
        setColumnConstraints(getColumnConstraints() + options.getSectionGap() + "![]");
    }

    /**
     * Initializes the layout for the task board.
     */
    private void setupBoardLayout() {

        String layoutConstraints = "flowy, insets 16 24 0 14, fillx, wrap 2, nocache";
        String rowConstraints = "[grow 0]" + options.getCardGap() + "![]";

        setConstraints(layoutConstraints, getColumnConstraints(), rowConstraints);
    }
}
