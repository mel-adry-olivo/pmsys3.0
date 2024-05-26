package org.pmsys.main.ui.views;

import org.pmsys.main.ui.components.constants.IconConstants;
import org.pmsys.main.actions.Actions;
import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.Task;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.ui.components.TaskBoard;
import org.pmsys.main.ui.components.TaskCard;
import org.pmsys.main.ui.components.base.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProjectView extends CPanel{

    private Project currentProjectOpened;

    private TaskBoard taskBoard;

    private CLabel projectTitle;

    private CButton exportButton;
    private CButton optionButton;
    private CButton closeButton;
    private CButton addTaskButton;

    public ProjectView() {
        super("insets 0, flowy, fillx", "fill", "[]0[]0[]");
        setupView();
        attachListeners();
    }

    public Project getCurrentProject() {
        return currentProjectOpened;
    }

    public void addTaskToView(TaskCard taskCard) {
        taskBoard.addTask(taskCard);
    }

    public void removeTaskFromView(TaskCard taskCard) {
        taskBoard.removeTask(taskCard);
    }

    // if status changed
    public void updateTaskInView(TaskCard taskCard, String oldStatus) {
        taskBoard.updateTask(taskCard, oldStatus);
    }

    public TaskCard createTaskCard(Task task) {
        TaskCard taskCard = new TaskCard(task);
        taskCard.handleOptionClick((e) -> ActionManager.executeAction(Actions.SHOW_TASK_OPTIONS, (CButton)e.getSource(), taskCard));
        return taskCard;
    }

    public void resetProjectView() {
        taskBoard.resetBoard();
        currentProjectOpened = null;
        projectTitle.setText("Project Title");
    }

    public void initProjectView(Project project) {
        taskBoard.resetBoard();
        currentProjectOpened = project;
        projectTitle.setText(currentProjectOpened.getTitle());
    }

    public void attachListeners() {
       exportButton.addActionListener(e -> ActionManager.executeAction(Actions.SHOW_INDIVIDUAL_REPORT_DIALOG, exportButton, this));
        optionButton.addActionListener(e -> ActionManager.executeAction(Actions.SHOW_PROJECT_OPTIONS, optionButton, this));
        addTaskButton.addActionListener(e -> ActionManager.executeAction(Actions.SHOW_TASK_ADD_FORM, addTaskButton, this));
        closeButton.addActionListener(e -> ActionManager.executeAction(Actions.CLOSE_PROJECT, closeButton, this));
    }

    private void setupView() {
        CPanel headerOne = createHeader("[]push[]2%[]2%[]");
        projectTitle = CLabelFactory.createH1Label("Project Title");
        exportButton = CButtonFactory.createHoverableIconButton(IconConstants.EXPORT_ICON_SMALL);
        optionButton = CButtonFactory.createHoverableIconButton(IconConstants.KEBAB_ICON_SMALL);
        closeButton = CButtonFactory.createHoverableIconButton(IconConstants.CLOSE_ICON_SMALL);

        CPanel headerTwo = createHeader("[]push[]1%[]");
        CButton boardButton = CButtonFactory.createBorderlessButton("Board");
        boardButton.setSelected(true);
        addTaskButton = CButtonFactory.createFilledButton("Add Task", IconConstants.ADD_ICON_SMALL);

        add(headerOne, "h 14%, growx");
        headerOne.add(projectTitle, "grow");
        headerOne.add(exportButton, "growx");
        headerOne.add(optionButton);
        headerOne.add(closeButton);

        add(headerTwo, "h 8%, growx");
        headerTwo.add(boardButton, "grow");
        headerTwo.add(addTaskButton, "grow");

        taskBoard = new TaskBoard();
        add(taskBoard, "h 100%");
    }
    private CPanel createHeader(String columnConstraint) {
        return new CPanel("insets 16 24 16 24, filly", columnConstraint, "[]")
                .setMatteBorder(0, 0, 1, 0);
    }

    public static class OptionsPopup extends JPopupMenu {

        private JMenuItem editProjectDetails;
        private JMenu setProjectStatus;
        private JMenuItem statusInProgress;
        private JMenuItem statusDone;
        private JMenuItem statusOverdue;

        private JMenuItem deleteProject;

        public OptionsPopup() {
            setupComponent();
        }

        public void handleEditProjectClick(ActionListener listener) {
            editProjectDetails.addActionListener(listener);
        }

        public void handleDeleteProjectClick(ActionListener listener) {
            deleteProject.addActionListener(listener);
        }

        public void handleSetProjectStatusClick(ActionListener listener) {
            statusInProgress.addActionListener(listener);
            statusDone.addActionListener(listener);
            statusOverdue.addActionListener(listener);
        }

        private void setupComponent() {
            editProjectDetails = new JMenuItem("Edit Project Details");
            setProjectStatus = new JMenu("Set Project Status");

            statusInProgress = new JMenuItem("In Progress");
            statusDone = new JMenuItem("Done");
            statusOverdue = new JMenuItem("Overdue");

            setProjectStatus.add(statusInProgress);
            setProjectStatus.add(statusDone);
            setProjectStatus.add(statusOverdue);

            deleteProject = new JMenuItem("Delete Project");
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            add(editProjectDetails);
            add(setProjectStatus);
            add(deleteProject);
        }

        @Override
        public int getWidth() {
            return editProjectDetails.getWidth();
        }
    }
}
