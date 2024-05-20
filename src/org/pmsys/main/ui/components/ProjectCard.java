package org.pmsys.main.ui.components;

import org.pmsys.main.entities.Project;
import org.pmsys.main.ui.components.base.CLabel;
import org.pmsys.main.ui.components.base.CLabelFactory;
import org.pmsys.main.ui.components.base.CPanel;

import java.awt.*;

public class ProjectCard extends CPanel {

    private final Project project;

    private CLabel lblName;
    private CLabel lblDescription;
    private CLabel lblTaskProgress;
    private CLabel lblStatus;
    private CLabel lblDueDate;

    public ProjectCard(Project project) {
        this.project = project;
        setupCard();
        updateUIData();
    }

    public Project getProject() {
        return project;
    }

    private void updateUIData() {
        lblName.setText(project.getTitle());
        lblDescription.setText(project.getDescription());
        lblTaskProgress.setText(project.getTaskDoneCount() + " / " + project.getOverallTaskCount());
        lblStatus.setText(project.getStatus());
        lblDueDate.setText(project.getDueDate());
    }
    private void setupCard() {
        setConstraints("insets 16px 3% 16px 3%, fill", "", "center");
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setHoverable(true);

        lblName         = CLabelFactory.createSemiBoldLabel("");
        lblDescription  = CLabelFactory.createDefaultLabel("");
        lblTaskProgress = CLabelFactory.createDefaultLabel("");
        lblStatus       = CLabelFactory.createDefaultLabel("");
        lblDueDate      = CLabelFactory.createDefaultLabel("");

        add(lblName , "growx, w 20%");
        add(lblDescription, "growx, w 20%");
        add(lblTaskProgress, "growx, w 20%");
        add(lblStatus, "growx, w 20%");
        add(lblDueDate, "growx, w 20%");
    }
}
