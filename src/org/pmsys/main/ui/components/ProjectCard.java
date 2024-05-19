package org.pmsys.main.ui.components;

import org.pmsys.main.model.Project;
import org.pmsys.main.ui.components.base.FlatLabel;
import org.pmsys.main.ui.components.base.FlatLabelFactory;
import org.pmsys.main.ui.components.base.FlatPanel;
import org.pmsys.main.ui.listeners.ProjectOpenListener;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProjectCard extends FlatPanel{

    private final Project project;

    private FlatLabel lblName;
    private FlatLabel lblDescription;
    private FlatLabel lblTaskProgress;
    private FlatLabel lblStatus;
    private FlatLabel lblDueDate;

    public ProjectCard(Project project) {
        this.project = project;
        setupCard();
        updateUIData();
    }

    public void handleCardClick(ProjectOpenListener listener) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.onProjectOpened(project);
            }
        });
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

        lblName         = FlatLabelFactory.createSemiBoldLabel("");
        lblDescription  = FlatLabelFactory.createDefaultLabel("");
        lblTaskProgress = FlatLabelFactory.createDefaultLabel("");
        lblStatus       = FlatLabelFactory.createDefaultLabel("");
        lblDueDate      = FlatLabelFactory.createDefaultLabel("");

        add(lblName , "growx, w 20%");
        add(lblDescription, "growx, w 20%");
        add(lblTaskProgress, "growx, w 20%");
        add(lblStatus, "growx, w 20%");
        add(lblDueDate, "growx, w 20%");
    }
}
