package org.pmsys.main.ui.views;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.entities.Project;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.managers.ViewManager;
import org.pmsys.main.ui.components.ProjectCard;
import org.pmsys.main.ui.components.ProjectList;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.components.constants.ColorConstants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DashboardView extends CPanel {

    private DataItem totalProjects;
    private DataItem doneProjects;
    private DataItem overdueProjects;
    private RecentProjects recentProjects;

    public DashboardView() {
        setupView();
    }

    public void setRecentProjects(List<Project> projects) {
        recentProjects.resetList();
        for (Project project : projects) {
            ProjectCard projectCard = new ProjectCard(project);
            projectCard.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ActionManager.executeAction(Actions.OPEN_PROJECT, projectCard, DashboardView.this);
                }
            });
            recentProjects.addProjectToUI(projectCard);
        }
    }

    public void setData(int totalProjects, int doneProjects, int overdueProjects) {
        this.totalProjects.setValue(totalProjects);
        this.doneProjects.setValue(doneProjects);
        this.overdueProjects.setValue(overdueProjects);
    }

    public void resetDashboard() {
        totalProjects.setValue(0);
        doneProjects.setValue(0);
        overdueProjects.setValue(0);
        recentProjects.resetList();
    }

    private void setupView() {
        setConstraints("insets 28, fill");

        CPanel mainContent = new CPanel()
                .setConstraints("insets 4, fillx", "[]0[]", "")
                .setLineBorder(1,1,1,1, 8);

        CPanel leftSection = createLeftSection();
        CPanel rightSection = createRightSection();

        mainContent.add(leftSection, "w 75%, h 100%, grow");
        mainContent.add(rightSection, "w 25%, h 100%, grow");

        add(mainContent, "grow");
    }

    private CPanel createLeftSection() {
        CPanel leftSection = new CPanel("insets 20 20 20 14, fillx", "[]3%[]3%[]", "[]3%[]2%[]");
        recentProjects = new RecentProjects();

        totalProjects = new DataItem("Total Projects");
        doneProjects = new DataItem("Done Projects");
        overdueProjects = new DataItem("Overdue Projects");

        leftSection.add(totalProjects, "w 31%, h 30%");
        leftSection.add(doneProjects, "w 31%, h 30%");
        leftSection.add(overdueProjects, "w 31%, h 30%, wrap");

        leftSection.add(CLabelFactory.createSemiBoldLabel("Recent Projects"), "w 100%, h 5%,span 3 1, wrap");
        leftSection.add(recentProjects, "h 60%, span 3 1, grow");
        return leftSection;
    }

    private CPanel createRightSection() {
        CPanel rightSection = new CPanel("insets 20 14 20 20, fillx", "", "");
        CImage image = new CImage("src/org/pmsys/resources/icons/pic.png");
        rightSection.add(image, "h 100%, growx");
        return rightSection;
    }

    private static class RecentProjects extends CPanel {

        private final ProjectList list;

        public RecentProjects() {
            setConstraints("insets 4 0 4 0 , fillx", "", "[]0[]");
            setLineBorder(1,1,1,1, 8);

            list = new ProjectList(4);
            ProjectList.Header header = new ProjectList.Header(0, 1);
            CPanel buttonPanel = new CPanel("insets 8 12 4 12, fill", "center", "center")
                    .setMatteBorder(1,0,0,0);
            CButton viewAllButton = CButtonFactory.createDefaultButton("View All Projects")
                    .setHoverBackgroundColor(ColorConstants.ACCENT)
                    .setHoverForegroundColor(ColorConstants.WHITE)
                    .applyStyles();
            viewAllButton.addActionListener(e -> ViewManager.INSTANCE.showView(Views.PROJECT_LIST));

            buttonPanel.add(viewAllButton, "growx");

            add(header, "h 1%, growx, wrap");
            add(list, "h 80%, growx, wrap");
            add(buttonPanel, "h 19%, growx");
        }

        public void addProjectToUI(ProjectCard card) {
            list.addProject(card);
        }

        public void resetList() {
            list.removeAllProjects();
        }
    }

    private static class DataItem extends CPanel {

        private final CLabel value;

        public DataItem(String name) {
            super("insets 24, fill");
            setLineBorder(1,1,1,1, 8);

            CLabel label = CLabelFactory.createDefaultLabel(name);
            value = CLabelFactory.createExtraScaledH1Label("0");

            add(label, "wrap");
            add(value, "wrap");
        }

        public void setValue(int value) {
            this.value.setText(String.valueOf(value));
        }
    }

    private static class CImage extends CPanel {

        private final BufferedImage image;
        private Image scaledImage;
        private int lastWidth = 0;
        private int lastHeight = 0;

        public CImage(String imagePath) {
            try {
                image = ImageIO.read(new File(imagePath));
                revalidate();
                repaint();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                int width = getWidth();
                int height = getHeight();

                // resize only if there is changes on width and height
                if (scaledImage == null || width != lastWidth || height != lastHeight) {
                    scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    lastWidth = width;
                    lastHeight = height;
                }

                g.drawImage(scaledImage, 0, 0, this);
            }
        }
    }
}
