package org.pmsys.main.ui.views;

import org.pmsys.constants.AppIcons;
import org.pmsys.main.actions.Actions;
import org.pmsys.main.controller.ProjectController;
import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.Task;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.ui.components.TaskBoard;
import org.pmsys.main.ui.components.TaskCard;
import org.pmsys.main.ui.components.base.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProjectView extends FlatPanel implements UIView{

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
        attachListeners(null);
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

    public void initProjectView(Project project) {
        taskBoard.resetBoard();
        currentProjectOpened = project;
        projectTitle.setText(currentProjectOpened.getTitle());
    }

    public void attachListeners(ProjectController controller) {
//        exportButton.addActionListener(controller::handleExportClick);
        optionButton.addActionListener(e -> {
            ActionManager.executeAction(Actions.SHOW_PROJECT_OPTIONS, optionButton, this);
        });
//        addTaskButton.addActionListener(controller::handleTaskAddClick);
        closeButton.addActionListener(e -> {
            ActionManager.executeAction(Actions.CLOSE_PROJECT, closeButton, this);
        });
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
