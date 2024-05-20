package org.pmsys.main.ui.views;

import org.pmsys.constants.AppIcons;
import org.pmsys.main.actions.Actions;
import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.Task;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.ui.CComponent;
import org.pmsys.main.ui.components.TaskBoard;
import org.pmsys.main.ui.components.TaskCard;
import org.pmsys.main.ui.components.base.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProjectView extends CPanel implements CComponent {

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
//        exportButton.addActionListener(controller::handleExportClick);
        optionButton.addActionListener(e -> ActionManager.executeAction(Actions.SHOW_PROJECT_OPTIONS, optionButton, this));
        addTaskButton.addActionListener(e -> ActionManager.executeAction(Actions.SHOW_TASK_ADD_FORM, addTaskButton, this));
        closeButton.addActionListener(e -> ActionManager.executeAction(Actions.CLOSE_PROJECT, closeButton, this));
    }

    private void setupView() {
        CPanel headerOne = createHeader("[]push[]2%[]2%[]");
        projectTitle = CLabelFactory.createH1Label("Project Title");
        exportButton = CButtonFactory.createHoverableIconButton(AppIcons.EXPORT_ICON_SMALL);
        optionButton = CButtonFactory.createHoverableIconButton(AppIcons.KEBAB_ICON_SMALL);
        closeButton = CButtonFactory.createHoverableIconButton(AppIcons.CLOSE_2_ICON_SMALL);

        CPanel headerTwo = createHeader("[]push[]1%[]");
        CButton boardButton = CButtonFactory.createBorderlessButton("Board");
        boardButton.setSelected(true);
        addTaskButton = CButtonFactory.createFilledButton("Add Task", AppIcons.ADD_ICON_SMALL);

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
                .setMatteBorder(0, 0, 1, 0)
                .applyFlatStyle();
    }

    public static class OptionsPopup extends JPopupMenu {

        private JMenuItem editProject;
        private JMenuItem deleteProject;

        public OptionsPopup() {
            setupComponent();
        }

        public void handleEditProjectClick(ActionListener listener) {
            editProject.addActionListener(listener);
        }

        public void handleDeleteProjectClick(ActionListener listener) {
            deleteProject.addActionListener(listener);
        }

        private void setupComponent() {
            editProject = new JMenuItem("Edit Project");
            deleteProject = new JMenuItem("Delete Project");
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            add(editProject);
            add(deleteProject);
        }
    }
}
