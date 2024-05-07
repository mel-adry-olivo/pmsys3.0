package org.pmsys.main.ui.components;

import org.pmsys.constants.AppColors;
import org.pmsys.constants.AppIcons;
import org.pmsys.main.entity.Task;
import org.pmsys.main.ui.components.base.FlatButtonFactory;
import org.pmsys.main.ui.components.base.FlatLabelFactory;
import org.pmsys.main.ui.components.base.FlatPanel;

import javax.swing.*;


public class TaskBoardSectionUI implements Section{

    private final HeaderUI header;
    private final FlatPanel container;

    private String rowConstraint = "";

    public TaskBoardSectionUI(String name) {
        header = new HeaderUI(name);
        container = new FlatPanel("insets 0, flowy", "", rowConstraint);
    }

    public void addTaskCard(Task task, String constraint) {
        rowConstraint += "[]0";
        container.setRowConstraints(rowConstraint);
        container.add(new TaskCardUI(task), constraint);
    }

    @Override
    public FlatPanel getSection() {
        return container;
    }

    @Override
    public HeaderUI getHeader() {
        return header;
    }

    public static class HeaderUI extends FlatPanel {

        private JLabel nameLabel;
        private JButton options;

        public HeaderUI(String name) {
            setupSectionHeader();
            setupComponents(name);
        }

        public JLabel getNameLabel() {
            return nameLabel;
        }
        public void setNameLabel(JLabel nameLabel) {
            this.nameLabel = nameLabel;
        }
        public JButton getOptions() {
            return options;
        }
        public void setOptions(JButton options) {
            this.options = options;
        }

        private void setupSectionHeader() {
            setConstraints("insets 10 12 10 12, filly", "[]push[]");
            setBackgroundColor(AppColors.WHITE);
            setLineBorder(1,1,1,1, 8);
            applyFlatStyle();
        }

        private void setupComponents(String name) {

            nameLabel = FlatLabelFactory.createMediumLabel(name, AppColors.BLACK);
            options = FlatButtonFactory.createIconButton(AppIcons.KEBAB_ICON_SMALL);

            add(nameLabel);
            add(options);
        }
    }
}
