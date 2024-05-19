package org.pmsys.main.ui.views;

import org.pmsys.constants.AppIcons;
import org.pmsys.main.controller.ProjectController;
import org.pmsys.main.model.Project;
import org.pmsys.main.model.Task;
import org.pmsys.main.ui.components.TaskBoard;
import org.pmsys.main.ui.components.TaskCard;
import org.pmsys.main.ui.components.base.*;


import javax.swing.*;
import java.awt.*;

public class ProjectView extends FlatPanel{

    private Project currentProjectOpened;

    private TaskBoard taskBoard;

    private FlatLabel projectTitle;

    private FlatButton exportButton;
    private FlatButton optionButton;
    private FlatButton closeButton;
    private FlatButton addTaskButton;

    public ProjectView() {
        super("insets 0, flowy, fillx", "fill", "[]0[]0[]");
        setupView();
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

    public TaskCard createTaskCard(Task task, ProjectController controller) {
        TaskCard taskCard = new TaskCard(task);
        taskCard.handleOptionClick(e -> controller.handleTaskOptionClick(e, taskCard));
        return taskCard;
    }

    public void resetProjectView() {
        taskBoard.resetBoard();
        currentProjectOpened = null;
        projectTitle.setText("Project Title");
    }

    public void updateProjectView(Project project) {
        taskBoard.resetBoard();
        currentProjectOpened = project;
        projectTitle.setText(currentProjectOpened.getTitle());
    }

    public void attachListeners(ProjectController controller) {
        exportButton.addActionListener(controller::handleExportClick);
        optionButton.addActionListener(controller::handleProjectOptionsClick);
        addTaskButton.addActionListener(controller::handleTaskAddClick);
        closeButton.addActionListener(controller::handleCloseClick);
    }

    private void setupView() {
        FlatPanel headerOne = createHeader("[]push[]2%[]2%[]");
        projectTitle = FlatLabelFactory.createH1Label("Project Title");
        exportButton = FlatButtonFactory.createHoverableIconButton(AppIcons.EXPORT_ICON_SMALL);
        optionButton = FlatButtonFactory.createHoverableIconButton(AppIcons.KEBAB_ICON_SMALL);
        closeButton = FlatButtonFactory.createHoverableIconButton(AppIcons.CLOSE_2_ICON_SMALL);

        FlatPanel headerTwo = createHeader("[]push[]1%[]");
        FlatButton boardButton = FlatButtonFactory.createBorderlessButton("Board");
        boardButton.setSelected(true);
        addTaskButton = FlatButtonFactory.createFilledButton("Add Task", AppIcons.ADD_ICON_SMALL);

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
    private FlatPanel createHeader(String columnConstraint) {
        return new FlatPanel("insets 16 24 16 24, filly", columnConstraint, "[]")
                .setMatteBorder(0, 0, 1, 0)
                .applyFlatStyle();
    }

    public static class OptionsPopup extends JPopupMenu {

        private final ProjectController projectController;

        private JMenuItem editProject;
        private JMenuItem deleteProject;

        public OptionsPopup(ProjectController projectController) {
            this.projectController = projectController;
            setupComponent();
            setupListeners();
        }

        private void setupListeners() {
            editProject.addActionListener(projectController::handleProjectEditClick);
            deleteProject.addActionListener(projectController::handleProjectDeleteClick);
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
