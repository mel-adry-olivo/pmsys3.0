package org.pmsys.main.ui.views;

import org.pmsys.constants.AppIcons;
import org.pmsys.main.ui.components.TaskCardUI;
import org.pmsys.main.ui.components.TaskSectionUI;
import org.pmsys.main.ui.components.base.FlatButtonFactory;
import org.pmsys.main.ui.components.base.FlatLabelFactory;
import org.pmsys.main.ui.components.base.FlatPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProjectView extends FlatPanel{

    private JPanel headerOne;
    private JPanel headerTwo;
    private JScrollPane scrollPane;
    private FlatPanel contentArea;

    private JLabel projectTitle;

    private JButton exportButton;
    private JButton optionButton;

    private JButton boardButton;
    private JButton listButton;
    private JButton sortButton;
    private JButton addTaskButton;

    private final String[] STATUS = {"Ready", "In Progress", "To Review", "Done"};

    public ProjectView() {
        super("insets 0, wrap, fillx", "[]", "[]0[]0[]");
        setupView();
        setupContentArea();
    }

    public void addTaskToSection(TaskCardUI task) {
        for (Component component : contentArea.getComponents()) {
            if (component instanceof TaskSectionUI) {
                if(((TaskSectionUI) component).getSectionName().equals(task.getStatus())) {
                    ((TaskSectionUI) component).addTaskCard(task);
                    break;
                }
            }
        }
        repaint();
        revalidate();
        contentArea.repaint();
        contentArea.revalidate();
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

        contentArea = createContentArea();
        contentArea.setPreferredSize(new Dimension(

        ));
        scrollPane = createScrollPane();
        scrollPane.getViewport().add(contentArea);

        add(scrollPane, "h 100%, grow");
    }
    private void setupContentArea() {
        for (String status : STATUS) {
            FlatPanel sectionPanel = createTaskCardSection(status);
            sectionPanel.setName(status);
            sectionPanel.setPreferredSize(new Dimension(sectionPanel.getWidth(), contentArea.getHeight()));
            contentArea.add(sectionPanel, "w 25%, h 100%");
        }
    }
    private FlatPanel createHeader(String columnConstraint) {
        return new FlatPanel("insets 16 26 16 26, filly", columnConstraint, "[]")
                .setMatteBorder(0, 0, 1, 0)
                .applyFlatStyle();
    }
    private JScrollPane createScrollPane() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollPane;
    }
    private FlatPanel createContentArea() {
        FlatPanel panel = new FlatPanel("insets 24 18 0 18, fillx", "[]16[]16[]16[]", "");
        panel.setPreferredSize(new Dimension(panel.getPreferredSize()));
        return panel;
    }
    private FlatPanel createTaskCardSection(String sectionName) {
        return new TaskSectionUI(sectionName);
    }
}
