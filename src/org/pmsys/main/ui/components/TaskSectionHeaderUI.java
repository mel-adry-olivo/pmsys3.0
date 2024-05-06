package org.pmsys.main.ui.components;

import org.pmsys.constants.AppColors;
import org.pmsys.constants.AppIcons;
import org.pmsys.main.ui.components.base.FlatButtonFactory;
import org.pmsys.main.ui.components.base.FlatLabelFactory;
import org.pmsys.main.ui.components.base.FlatPanel;

import javax.swing.*;

public class TaskSectionHeaderUI extends FlatPanel {

    private JLabel nameLabel;
    private JButton options;

    public TaskSectionHeaderUI(String name) {
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
        setConstraints("insets 8 12 8 12, filly", "[]push[]");
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
