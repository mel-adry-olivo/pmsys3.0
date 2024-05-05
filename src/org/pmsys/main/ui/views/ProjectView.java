package org.pmsys.main.ui.views;

import org.pmsys.constants.AppIcons;
import org.pmsys.main.ui.components.TaskSectionHeaderUI;
import org.pmsys.main.ui.components.base.FlatButtonFactory;
import org.pmsys.main.ui.components.base.FlatLabelFactory;
import org.pmsys.main.ui.components.base.FlatPanel;

import javax.swing.*;

public class ProjectView extends FlatPanel {

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
    private JButton addButton;

    private final String[] STATUS = {"Ready", "In Progress", "To Do", "Done"};

    public ProjectView() {
        super("insets 0, wrap, fillx", "[]", "[]0[]0[]");
        setupComponent();
        setupContentArea();
    }

    private void setupContentArea() {
        //contentArea.removeAll();
        for (String status : STATUS) {
            FlatPanel sectionPanel = createTaskCardSection(status);
            contentArea.add(sectionPanel, "w 25%, h 100%");
        }
    }

    private void setupComponent() {

        headerOne = createHeader("[]push[]2%[]");
        projectTitle = FlatLabelFactory.createH1Label("Project Title");
        exportButton = FlatButtonFactory.createDefaultButton("Export", AppIcons.EXPORT_ICON_SMALL);
        optionButton = FlatButtonFactory.createHoverableIconButton(AppIcons.KEBAB_ICON_SMALL);

        headerTwo = createHeader("[]0%[]push[]1%[]");
        boardButton = FlatButtonFactory.createBorderlessButton("Board");
        listButton = FlatButtonFactory.createBorderlessButton("List");
        sortButton = FlatButtonFactory.createDefaultButton("Sort");
        addButton = FlatButtonFactory.createFilledButton("Add Task", AppIcons.ADD_ICON_SMALL);

        contentArea = createContentArea();
        scrollPane = createScrollPane(contentArea);

        add(headerOne, "h 14%, growx");
        headerOne.add(projectTitle, "grow");
        headerOne.add(exportButton, "growx");
        headerOne.add(optionButton);

        add(headerTwo, "h 8%, growx");
        headerTwo.add(boardButton, "grow");
        headerTwo.add(listButton, "grow");
        headerTwo.add(sortButton, "grow");
        headerTwo.add(addButton, "grow");

        add(scrollPane, "h 100%, grow");
    }

    private FlatPanel createHeader(String columnConstraint) {
        return new FlatPanel("insets 16 26 16 26, filly", columnConstraint, "[]")
                .setMatteBorder(0, 0, 1, 0)
                .applyFlatStyle();
    }

    private JScrollPane createScrollPane(FlatPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollPane;
    }

    private FlatPanel createContentArea() {
        return new FlatPanel("insets 28 18 0 18, fillx", "[]16[]16[]16[]", "");
    }

    private FlatPanel createTaskCardSection(String sectionName) {

        FlatPanel section = new FlatPanel("insets 25, wrap")
                .setLineBorder(1,1,1,1, 8)
                .applyFlatStyle();

        TaskSectionHeaderUI header = new TaskSectionHeaderUI(sectionName);
        section.add(header, "w 100%, h 0%");

        return section;
    }

//        MigLayout layout = new MigLayout("insets 0, wrap");
//        JPanel sectionPanel = ComponentFactory.Panels.createDefaultPanel(layout, ComponentFactory.TASK_SECTION_SIZE);
//        TaskSectionHeader header = ComponentFactory.Custom.createTaskStatusHeader(section.getName());
//
//        sectionPanel.add(header);
//        for (Task task : section.getTasks()) {
//            TaskCard taskCard = ComponentFactory.Custom.createTaskCard(task);
//            sectionPanel.add(taskCard);
//        }
//
//
//        return sectionPanel;



}
