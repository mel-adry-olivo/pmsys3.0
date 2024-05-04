package org.pmsys.main.ui.components;

import org.pmsys.constants.AppColors;
import org.pmsys.main.entity.Project;
import org.pmsys.main.ui.components.base.FlatLabel;
import org.pmsys.main.ui.components.base.FlatPanel;
import org.pmsys.main.ui.listeners.ProjectCardListener;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProjectCardUI extends FlatPanel{

    private Project project;

    private FlatLabel lblName;
    private FlatLabel lblDescription;
    private FlatLabel lblTaskProgress;
    private FlatLabel lblStatus;
    private FlatLabel lblDueDate;

    public ProjectCardUI(Project project) {
        this.project = project;
        setupItem();

    }

    public void handleProjectOpen(ProjectCardListener listener) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.onClick(project);
            }
        });
    }

    public void updateData(Project newProject) {
        project = newProject;
        updateUIData();
    }

    private void updateUIData() {
        lblName.setText(project.getTitle());
        lblDescription.setText(project.getDescription());
        lblTaskProgress.setText(project.getTaskProgress() + "%");
        lblStatus.setText(project.getStatus());
        lblDueDate.setText(project.getDueDate());
    }
    private void setupItem() {
        setConstraints("insets 16px 3% 16px 3%, fill", "", "center");
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setHoverable(true);

        lblName         = createLabel(FlatLabel.SEMIBOLD, AppColors.BLACK);
        lblDescription  = createLabel(FlatLabel.DEFAULT, AppColors.DARK_GREY);
        lblTaskProgress = createLabel(FlatLabel.DEFAULT, AppColors.DARK_GREY);
        lblStatus       = createLabel(FlatLabel.DEFAULT, AppColors.DARK_GREY);
        lblDueDate      = createLabel(FlatLabel.DEFAULT, AppColors.DARK_GREY);

        add(lblName , "growx, w 20%");
        add(lblDescription, "growx, w 20%");
        add(lblTaskProgress, "growx, w 20%");
        add(lblStatus, "growx, w 20%");
        add(lblDueDate, "growx, w 20%");

        updateUIData();
    }

    private FlatLabel createLabel(String fontStyle, String foregroundColor) {
        return new FlatLabel("")
                .setFontStyle(fontStyle)
                .setForegroundColor(foregroundColor)
                .applyFlatStyle();
    }
}
