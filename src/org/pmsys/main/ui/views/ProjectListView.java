package org.pmsys.main.ui.views;

import org.pmsys.main.ui.components.constants.IconConstants;
import org.pmsys.main.actions.Actions;
import org.pmsys.main.entities.Project;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.ui.components.ProjectCard;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.components.ProjectList;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProjectListView extends CPanel{

    private int currentProjectPage = 0;
    private int currentTotalPage = 0;

    private final CardLayout projectPagesLayout = new CardLayout();
    private CLabel pageLabel;
    private CPanel listPagesContainer;

    private CButton addProjectButton;
    private CButton exportButton;
    private CButton previousButton;
    private CButton nextButton;

    private ProjectList currentProjectList;

    public ProjectListView() {
        setupView();
        attachListeners();
    }


    public ProjectCard createProjectCard(Project project) {
        ProjectCard projectCard = new ProjectCard(project);
        projectCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ActionManager.executeAction(Actions.OPEN_PROJECT, projectCard, ProjectListView.this);
            }
        });

        return projectCard;
    }

    public void resetProjectList() {
        if (currentProjectList != null) {
            currentProjectList.removeAllProjects();
        }

        if(listPagesContainer != null) {
            listPagesContainer.removeAll();

        }

        currentProjectPage = 0;
        currentTotalPage = 0;

        updatePageLabel();
        updateButtonState();

        currentProjectList = createProjectList();
        if (currentProjectList != null) {
            addListToUI(currentProjectList, String.valueOf(currentTotalPage));
        }
    }

    public void addProjectToUI(ProjectCard card) {
        if (currentProjectList.isFull()) {
            currentProjectList = createProjectList();
            addListToUI(currentProjectList, String.valueOf(currentProjectPage));
        }
        currentProjectList.addProject(card);
        updateButtonState();
    }

    private void addListToUI(ProjectList list, String name) {
        listPagesContainer.add(list, name);
        projectPagesLayout.show(listPagesContainer, name);

        currentTotalPage++;
        currentProjectPage = currentTotalPage;
        updatePageLabel();
    }

    public void showNextPage() {
        if (hasNextPage()) {
            currentProjectPage++;
            projectPagesLayout.next(listPagesContainer);
            updatePageLabel();
        }
        updateButtonState();
    }

    public void showPreviousPage() {
        if (hasPreviousPage()) {
            projectPagesLayout.previous(listPagesContainer);
            currentProjectPage--;
            updatePageLabel();
        }
        updateButtonState();
    }

    public boolean hasNextPage() {
        return currentProjectPage < currentTotalPage && currentTotalPage != 1 && currentProjectPage >= 1;
    }

    public boolean hasPreviousPage() {
        return currentProjectPage > 1 && currentTotalPage > 1 && currentProjectPage <= currentTotalPage;
    }

    public void updateButtonState() {
        previousButton.setEnabled(hasPreviousPage());
        nextButton.setEnabled(hasNextPage());
    }

    private void updatePageLabel() {
        pageLabel.setText("Page " + currentProjectPage + " of " + currentTotalPage);
    }

    public void attachListeners() {
        nextButton.addActionListener(e -> {
            ActionManager.executeAction(Actions.NEXT_PAGE, nextButton, this);
        });
        previousButton.addActionListener(e -> {
            ActionManager.executeAction(Actions.PREVIOUS_PAGE, nextButton, this);
        });
//        exportButton.addActionListener(controller::handleExportButtonClick);

        addProjectButton.addActionListener(e -> {
            ActionManager.executeAction(Actions.SHOW_PROJECT_ADD_FORM, addProjectButton, this);
        });

    }

    public void goToFirstPage() {
        currentProjectPage = 1;
        projectPagesLayout.first(listPagesContainer);
        updateButtonState();
    }


    public void setupView() {
        this.setConstraints("insets 28, fill");

        CPanel mainContent = new CPanel()
                .setConstraints("insets 0, fillx", "", "[]0[]0[]0[]")
                .setLineBorder(1,1,1,1, 8);

        listPagesContainer = createPageContainer();

        CPanel headerSection = createHeaderSection();
        CPanel sortSection = createSortSection();
        CPanel footerSection = createFooterSection();
        CPanel listHeader = ComponentFactory.createListHeader();

        if (currentProjectList == null) {
            addListToUI(currentProjectList = createProjectList(), String.valueOf((currentTotalPage)));
        }

        mainContent.add(headerSection, "gaptop 5, h 0%, grow, wrap");
        mainContent.add(sortSection, "h 1%, grow, wrap");
        mainContent.add(listHeader, "h 1%, grow, wrap");
        mainContent.add(listPagesContainer, "h 96%, wrap, grow");
        mainContent.add(footerSection, "h 2%, wrap, grow, gapbottom 5");

        add(mainContent, "grow");

    }
    private CPanel createHeaderSection() {

        CPanel header = new CPanel("insets 16 3% 16 3%, filly", "[]push[]2%[]", "center");

        CLabel allProjectsLabel = CLabelFactory.createH1Label("All Projects");

        addProjectButton = CButtonFactory.createFilledButton("Add Project", IconConstants.ADD_ICON_SMALL);
        exportButton = CButtonFactory.createHoverableIconButton(IconConstants.EXPORT_ICON_SMALL);

        header.add(allProjectsLabel, "gapbottom 5");
        header.add(exportButton, "gapbottom 5, hmin 32, hmax 32");
        header.add(addProjectButton, "gapbottom 5, hmin 32, hmax 32");

        return header;
    }
    private CPanel createSortSection() {
        return new CPanel()
                .setConstraints("insets 16 28 16 28, filly", "", "center")
                .setMatteBorder(1,0,0,0)
                .applyStyles();
    }
    private CPanel createFooterSection() {
        CPanel footer = new CPanel()
                .setConstraints("insets 4 28 4 28, filly", "", "center")
                .setMatteBorder(1,0,0,0)
                .applyStyles();

        previousButton = CButtonFactory.createDefaultButton("Previous");
        nextButton = CButtonFactory.createDefaultButton("Next");

        pageLabel = CLabelFactory.createSmallLabel("Page " + currentProjectPage + " of " + currentTotalPage);

        footer.add(previousButton, "gaptop 5, hmin 32, hmax 32");
        footer.add(nextButton, "gaptop 5, hmin 32, hmax 32, pushx");
        footer.add(pageLabel, "gaptop 5, hmin 32, hmax 32");

        return footer;
    }
    private CPanel createPageContainer() {
        CPanel pageContainer = new CPanel("insets 0");
        pageContainer.setLayout(projectPagesLayout);
        return pageContainer;
    }
    public ProjectList createProjectList() {
        return new ProjectList();
    }

    private static class ComponentFactory {
        private static CPanel createListHeader() {
            CPanel projectListHeader = new CPanel()
                    .setConstraints("insets 6px 3% 6px 3%, fill", "", "center")
                    .setMatteBorder(1,0,1,0)
                    .applyStyles();

            CLabel nameColumn = CLabelFactory.createSmallLabel("Project Name");
            CLabel descriptionColumn = CLabelFactory.createSmallLabel("Description");
            CLabel taskProgressColumn = CLabelFactory.createSmallLabel("Task Progress");
            CLabel statusColumn = CLabelFactory.createSmallLabel("Status");
            CLabel dueDateColumn = CLabelFactory.createSmallLabel("Due Date");

            projectListHeader.add(nameColumn, "w 20%");
            projectListHeader.add(descriptionColumn, "w 20%");
            projectListHeader.add(taskProgressColumn, "w 20%");
            projectListHeader.add(statusColumn, "w 20%");
            projectListHeader.add(dueDateColumn, "w 20%");
            return projectListHeader;
        }
    }
}
