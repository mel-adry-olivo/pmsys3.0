package org.pmsys.main.ui.views;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import org.pmsys.constants.AppIcons;
import org.pmsys.main.ui.components.TaskBoard;
import org.pmsys.main.ui.components.TaskCardUI;
import org.pmsys.main.ui.components.TaskSectionUI;
import org.pmsys.main.ui.components.base.FlatButtonFactory;
import org.pmsys.main.ui.components.base.FlatLabelFactory;
import org.pmsys.main.ui.components.base.FlatPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProjectView extends FlatPanel{

    private TaskBoard board;

    private JPanel headerOne;
    private JPanel headerTwo;

    private JLabel projectTitle;

    private JButton exportButton;
    private JButton optionButton;

    private JButton boardButton;
    private JButton listButton;
    private JButton sortButton;
    private JButton addTaskButton;


    public ProjectView() {
        super("insets 0, flowy, fillx", "fill", "[]0[]0[]");
        setupView();
    }

    public void addTaskToSection(TaskCardUI task) {
        board.addTaskCard(task);
    }

    public void addAddTaskButtonListener(ActionListener listener) {
        addTaskButton.addActionListener(listener);
    }

    public void setupView() {

        headerOne = createHeader("[]push[]2%[]");
        projectTitle = FlatLabelFactory.createH1Label("Project Title");
        exportButton = FlatButtonFactory.createDefaultButton("Export", AppIcons.EXPORT_ICON_SMALL);
        optionButton = FlatButtonFactory.createHoverableIconButton(AppIcons.KEBAB_ICON_SMALL);

        headerTwo = createHeader("[]0%[]push[]1%[]");
        boardButton = FlatButtonFactory.createBorderlessButton("Board");
        boardButton.setSelected(true);
        listButton = FlatButtonFactory.createBorderlessButton("List");
        sortButton = FlatButtonFactory.createDefaultButton("Sort");
        addTaskButton = FlatButtonFactory.createFilledButton("Add Task", AppIcons.ADD_ICON_SMALL);

        add(headerOne, "h 14%, growx");
        headerOne.add(projectTitle, "grow");
        headerOne.add(exportButton, "growx");
        headerOne.add(optionButton);

        add(headerTwo, "h 8%, growx");
        headerTwo.add(boardButton, "grow");
        headerTwo.add(listButton, "grow");
        headerTwo.add(sortButton, "grow");
        headerTwo.add(addTaskButton, "grow");

        board = new TaskBoard();
        add(board, "h 100%");
    }

    private FlatPanel createHeader(String columnConstraint) {
        FlatPanel panel = new FlatPanel("insets 16 24 16 24, filly", columnConstraint, "[]")
                .setMatteBorder(0, 0, 1, 0)
                .applyFlatStyle();

        return panel;
    }
}
