package org.pmsys.main.ui.views;

import org.pmsys.constants.AppIcons;
import org.pmsys.main.actions.Actions;
import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.request.Request;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.ui.components.ProjectCard;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.forms.ProjectSimpleForm;
import org.pmsys.main.ui.components.ProjectList;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class represents the main view for displaying a list of projects.
 */
public class ProjectListView extends FlatPanel implements UIView{

    private int currentProjectPage = 0;
    private int currentTotalPage = 0;

    private final CardLayout projectPagesLayout = new CardLayout();
    private FlatLabel pageLabel;
    private FlatPanel projectPagesContainer;

    private FlatButton addProjectButton;
    private FlatButton exportButton;
    private FlatButton previousButton;
    private FlatButton nextButton;

    private ProjectList currentProjectList;
    private ProjectSimpleForm projectCreateForm;

    public ProjectListView() {
        setupView();
        attachListeners();
        requestFocusInWindow();
    }

    public ProjectSimpleForm getProjectForm() {
        return projectCreateForm;
    }

    public Request getProjectRequest() {
        return projectCreateForm.getFormData();
    }

    public ProjectCard createProjectCard(Project project) {
        ProjectCard projectCard = new ProjectCard(project);
        projectCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ActionManager.executeAction(Actions.OPEN_PROJECT, projectCard, ProjectListView.this);
            }
        });

        projectCard.requestFocusInWindow();
        return projectCard;
    }

    public void removeAllProjectCards() {
        if (currentProjectList != null) {
            currentProjectList.removeAllProjects();
        }

        projectPagesContainer.removeAll();
        currentProjectPage = 0;
        currentTotalPage = 0;
        updatePageLabel();
        updateButtonState();

        addListToUI(currentProjectList = createProjectList(), String.valueOf(currentTotalPage));
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
        projectPagesContainer.add(list, name);
        projectPagesLayout.show(projectPagesContainer, name);

        currentTotalPage++;
        currentProjectPage = currentTotalPage;
        updatePageLabel();
    }

    public void showNextPage() {
        if (hasNextPage()) {
            currentProjectPage++;
            projectPagesLayout.next(projectPagesContainer);
            updatePageLabel();
        }
        updateButtonState();
    }

    public void showPreviousPage() {
        if (hasPreviousPage()) {
            projectPagesLayout.previous(projectPagesContainer);
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
        projectPagesLayout.first(projectPagesContainer);
        updateButtonState();
    }


















    public void setupView() {
        this.setConstraints("insets 28, fill");

        FlatPanel mainContent = new FlatPanel()
                .setConstraints("insets 0, fillx", "", "[]0[]0[]0[]")
                .setLineBorder(1,1,1,1, 8)
                .applyFlatStyle();

        projectCreateForm = new ProjectSimpleForm();
        projectPagesContainer = createPageContainer();

        FlatPanel headerSection = createHeaderSection();
        FlatPanel sortSection = createSortSection();
        FlatPanel footerSection = createFooterSection();
        FlatPanel listHeader = ComponentFactory.createListHeader();

        if (currentProjectList == null) {
            addListToUI(currentProjectList = createProjectList(), String.valueOf((currentTotalPage)));
        }

        mainContent.add(headerSection, "gaptop 5, h 0%, grow, wrap");
        mainContent.add(sortSection, "h 1%, grow, wrap");
        mainContent.add(listHeader, "h 1%, grow, wrap");
        mainContent.add(projectPagesContainer, "h 96%, wrap, grow");
        mainContent.add(footerSection, "h 2%, wrap, grow, gapbottom 5");

        add(mainContent, "grow");

    }
    private FlatPanel createHeaderSection() {

        FlatPanel header = new FlatPanel("insets 16 3% 16 3%, filly", "[]push[]2%[]", "center");

        FlatLabel allProjectsLabel = FlatLabelFactory.createH1Label("All Projects");

        addProjectButton = FlatButtonFactory.createFilledButton("Add Project", AppIcons.ADD_ICON_SMALL);
        exportButton = FlatButtonFactory.createHoverableIconButton(AppIcons.EXPORT_ICON_SMALL);

        header.add(allProjectsLabel, "gapbottom 5");
        header.add(exportButton, "gapbottom 5, hmin 32, hmax 32");
        header.add(addProjectButton, "gapbottom 5, hmin 32, hmax 32");

        return header;
    }
    private FlatPanel createSortSection() {
        return new FlatPanel()
                .setConstraints("insets 16 28 16 28, filly", "", "center")
                .setMatteBorder(1,0,0,0);
    }
    private FlatPanel createFooterSection() {
        FlatPanel footer = new FlatPanel()
                .setConstraints("insets 4 28 4 28, filly", "", "center")
                .setMatteBorder(1,0,0,0);

        previousButton = FlatButtonFactory.createDefaultButton("Previous");
        nextButton = FlatButtonFactory.createDefaultButton("Next");

        pageLabel = FlatLabelFactory.createSmallLabel("Page " + currentProjectPage + " of " + currentTotalPage);

        footer.add(previousButton, "gaptop 5, hmin 32, hmax 32");
        footer.add(nextButton, "gaptop 5, hmin 32, hmax 32, pushx");
        footer.add(pageLabel, "gaptop 5, hmin 32, hmax 32");

        return footer;
    }
    private FlatPanel createPageContainer() {
        FlatPanel pageContainer = new FlatPanel("insets 0");
        pageContainer.setLayout(projectPagesLayout);
        return pageContainer;
    }
    public ProjectList createProjectList() {
        return new ProjectList();
    }

    private static class ComponentFactory {
        private static FlatPanel createListHeader() {
            FlatPanel projectListHeader = new FlatPanel()
                    .setConstraints("insets 6px 3% 6px 3%, fill", "", "center")
                    .setMatteBorder(1,0,1,0);

            FlatLabel nameColumn = FlatLabelFactory.createSmallLabel("Project Name");
            FlatLabel descriptionColumn = FlatLabelFactory.createSmallLabel("Description");
            FlatLabel taskProgressColumn = FlatLabelFactory.createSmallLabel("Task Progress");
            FlatLabel statusColumn = FlatLabelFactory.createSmallLabel("Status");
            FlatLabel dueDateColumn = FlatLabelFactory.createSmallLabel("Due Date");

            projectListHeader.add(nameColumn, "w 20%");
            projectListHeader.add(descriptionColumn, "w 20%");
            projectListHeader.add(taskProgressColumn, "w 20%");
            projectListHeader.add(statusColumn, "w 20%");
            projectListHeader.add(dueDateColumn, "w 20%");
            return projectListHeader;
        }
    }
}
