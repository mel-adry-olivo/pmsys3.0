package org.pmsys.main.ui.components;

import org.pmsys.main.entities.Task;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.components.constants.ColorConstants;
import org.pmsys.main.ui.components.constants.IconConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TaskCard extends CPanel{

    private final Task task;

    private final CLabel title;
    private final CButton optionButton;
    private final CLabel description;
    private final Tag priority;
    private final Tag dueDate;

    public TaskCard(Task task) {
        this.task = task;
        setConstraints("insets 12 12 12 12, fillx", "", "[]12[]12[]12[]");
        setLineBorder(1, 1, 1, 1, 8);
        applyStyles();

        title = CLabelFactory.createSemiBoldLabel(task.getTitle()).wrapOnIndex(28);
        optionButton = CButtonFactory.createIconButton(IconConstants.KEBAB_ICON_SMALL);
        description = CLabelFactory.createMediumLabel(task.getDescription(), ColorConstants.DARK_GREY).wrapOnIndex(28);
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

    public Task getTask() {
        return task;
    }

    public void handleOptionClick(ActionListener listener) {
        optionButton.addActionListener(listener);
    }

    private static class Tag extends CPanel {

        private CLabel label;

        private Tag(String text) {
            setConstraints("insets 2 12 2 12", "center", "center");
            String[] tagColor = getTagColor(text);
            if (tagColor  != null) {
                label = CLabelFactory.createSmallLabel(text, tagColor[0]);
                setLineBorder(1,1,1,1, tagColor[0], 6);
                setBackgroundColor(tagColor[1]);
                applyStyles();
                add(label, "h 0%");
            }
        }

        private void setText(String text) {
            label.setText(text);
        }
        private String[] getTagColor(String priority) {
            return switch (priority.toLowerCase()) {
                case "low" -> ColorConstants.TAG_LOW_BLUE;
                case "normal" -> ColorConstants.TAG_NORMAL_GREEN;
                case "high" -> ColorConstants.TAG_HIGH_RED;
                default -> ColorConstants.TAG_DATE_ACCENT;
            };
        }
    }

    public static class OptionsPopup extends JPopupMenu {

        private JMenuItem editTask;
        private JMenuItem statusReady;
        private JMenuItem statusInProgress;
        private JMenuItem statusToReview;
        private JMenuItem statusDone;
        private JMenuItem deleteTask;

        public OptionsPopup(TaskCard currentCard) {
            setupComponent(currentCard.getTask().getStatus());
        }

        public void handleEditProjectClick(ActionListener listener) {
            editTask.addActionListener(listener);
        }

        public void handleDeleteProjectClick(ActionListener listener) {
            deleteTask.addActionListener(listener);
        }

        public void handleSetTaskStatusClick(ActionListener listener) {
            statusReady.addActionListener(listener);
            statusInProgress.addActionListener(listener);
            statusToReview.addActionListener(listener);
            statusDone.addActionListener(listener);
        }

        private void setupComponent(String status) {
            editTask = new JMenuItem("Edit Task");

            JMenu setTaskStatus = new JMenu("Set Status");
            statusReady = new JMenuItem("Ready");
            statusInProgress = new JMenuItem("In Progress");
            statusToReview = new JMenuItem("To Review");
            statusDone = new JMenuItem("Done");

            if (!status.equalsIgnoreCase("Ready")) {
                setTaskStatus.add(statusReady);
            }
            if (!status.equalsIgnoreCase("In Progress")) {
                setTaskStatus.add(statusInProgress);
            }
            if (!status.equalsIgnoreCase("To Review")) {
                setTaskStatus.add(statusToReview);
            }
            if (!status.equalsIgnoreCase("Done")) {
                setTaskStatus.add(statusDone);
            }

            deleteTask = new JMenuItem("Delete Task");

            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            add(editTask);
            add(setTaskStatus);
            add(deleteTask);
        }
    }
}