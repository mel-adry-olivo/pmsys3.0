package org.pmsys.main.ui.views;

import org.pmsys.constants.AppIcons;
import org.pmsys.main.controller.ProjectListController;
import org.pmsys.main.model.Project;
import org.pmsys.main.model.request.Request;
import org.pmsys.main.ui.components.ProjectCard;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.forms.ProjectFormUI;
import org.pmsys.main.ui.components.ProjectList;
import org.pmsys.main.ui.listeners.ProjectOpenListener;

import java.awt.*;

/**
 * <h1>ProjectListView</h1>
 * This class represents the main view for displaying a list of projects.
 */
public class ProjectListView extends FlatPanel{

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
    private ProjectFormUI projectCreateForm;

    public ProjectListView() {
        setupView();
    }

    public void showProjectForm() {
        projectCreateForm.showForm();
    }

    public ProjectFormUI getProjectForm() {
        return projectCreateForm;
    }

    public Request getProjectRequest() {
        return projectCreateForm.getFormData();
    }

    public ProjectCard createProjectCard(Project project, ProjectOpenListener listener) {
        ProjectCard projectCard = new ProjectCard(project);
        projectCard.handleCardClick(listener);
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
    }

    public void showPreviousPage() {
        if (hasPreviousPage()) {
            projectPagesLayout.previous(projectPagesContainer);
            currentProjectPage--;
            updatePageLabel();
        }
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

    public void attachListeners(ProjectListController controller) {
        nextButton.addActionListener(controller::handleNextButtonClick);
        previousButton.addActionListener(controller::handlePreviousButtonClick);
        exportButton.addActionListener(controller::handleExportButtonClick);
        addProjectButton.addActionListener(controller::handleAddProjectButtonClick);
        projectCreateForm.handleActionButton(controller::handleProjectCreationEvent);
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

        projectCreateForm = new ProjectFormUI();
        projectPagesContainer = createPageContainer();

        FlatPanel headerSection = createHeaderSection();
        FlatPanel sortSection = createSortSection();
        FlatPanel footerSection = createFooterSection();
        FlatPanel listHeader = createProjectListHeader();

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
    private FlatPanel createProjectListHeader() {

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
    public ProjectList createProjectList() {
        return new ProjectList();
    }

}
