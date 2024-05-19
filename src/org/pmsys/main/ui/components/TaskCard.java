package org.pmsys.main.ui.components;

import org.pmsys.constants.AppColors;
import org.pmsys.constants.AppIcons;
import org.pmsys.main.controller.ProjectController;
import org.pmsys.main.model.Task;
import org.pmsys.main.ui.components.base.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TaskCard extends FlatPanel {

    private final Task task;

    private final FlatLabel title;
    private final FlatButton optionButton;
    private final FlatLabel description;
    private final Tag priority;
    private final Tag dueDate;

    public TaskCard(Task task) {
        this.task = task;
        setConstraints("insets 12 12 12 12, fillx", "", "[]12[]12[]12[]");
        setLineBorder(1, 1, 1, 1, 8);
        applyFlatStyle();

        title = FlatLabelFactory.createSemiBoldLabel(task.getTitle()).wrapOnIndex(28);
        optionButton = FlatButtonFactory.createIconButton(AppIcons.KEBAB_ICON_SMALL);
        description = FlatLabelFactory.createMediumLabel(task.getDescription(), AppColors.DARK_GREY).wrapOnIndex(28);
        priority = new Tag(task.getPriority());
        dueDate = new Tag(task.getDueDate());

        add(title, "pushx");
        add(optionButton, "wrap");
        add(description, "wrap");
        add(priority, "w 0%, h 0%, split 2");
        add(dueDate, "w 0%, h 0%, wrap");
    }

    public void updateTaskDetails(Task updatedTask) {
        title.setText(updatedTask.getTitle());
        title.wrapOnIndex(28);

        description.setText(updatedTask.getDescription());
        description.wrapOnIndex(28);

        priority.setText(updatedTask.getPriority());
        dueDate.setText(updatedTask.getDueDate());

        repaint();
        revalidate();
    }

    public Task get() {
        return task;
    }

    public void handleOptionClick(ActionListener listener) {
        optionButton.addActionListener(listener);
    }

    private static class Tag extends FlatPanel {

        private FlatLabel label;

        private Tag(String text) {
            setConstraints("insets 2 12 2 12", "center", "center");
            String[] tagColor = getTagColor(text);
            if (tagColor  != null) {
                label = FlatLabelFactory.createSmallLabel(text, tagColor[0]);
                setLineBorder(1,1,1,1, tagColor[0], 6);
                setBackgroundColor(tagColor[1]);
                applyFlatStyle();
                add(label, "h 0%");
            }
        }

        private void setText(String text) {
            label.setText(text);
        }
        private String[] getTagColor(String priority) {
            return switch (priority.toLowerCase()) {
                case "low" -> AppColors.TAG_LOW_BLUE;
                case "normal" -> AppColors.TAG_NORMAL_GREEN;
                case "high" -> AppColors.TAG_HIGH_RED;
                default -> AppColors.TAG_DATE_ACCENT;
            };
        }
    }

    public static class OptionsPopup extends JPopupMenu {

        private final ProjectController projectController;
        private final TaskCard currentCard;

        private JMenuItem editTask;
        private JMenuItem deleteTask;

        public OptionsPopup(ProjectController projectController, TaskCard currentCard) {
            this.projectController = projectController;
            this.currentCard = currentCard;
            setupComponent();
            setupListeners();
        }

        private void setupListeners() {
            editTask.addActionListener(e -> projectController.handleTaskEditClick(currentCard));
            deleteTask.addActionListener(e -> projectController.handleTaskDeleteClick(currentCard));
        }

        private void setupComponent() {
            editTask = new JMenuItem("Edit Task");
            deleteTask = new JMenuItem("Delete Task");

            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            add(editTask);
            add(deleteTask);
        }
    }
}