package org.pmsys.main.ui.views;

import org.pmsys.constants.AppColors;
import org.pmsys.constants.AppIcons;
import org.pmsys.main.entity.ProjectCreateRequest;
import org.pmsys.main.ui.components.ProjectCardUI;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.forms.ProjectCreateUI;
import org.pmsys.main.ui.components.ProjectListUI;
import org.pmsys.main.ui.listeners.ClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/**
 * <h1>ProjectListView</h1>
 * This class represents the main view for displaying a list of projects.
 * It provides the following features:
 * <li>Adding new projects</li>
 * <li>Sorting projects</li>
 * <li>Exporting project data</li>
 * <li>Pagination through multiple pages of projects</li>
 * <li>Embedded project creation form</li>
 */
public class ProjectListView extends FlatPanel{

    private int currentProjectPage = 0;
    private int currentTotalPage = 0;

    private final CardLayout projectPagesLayout = new CardLayout();
    private FlatLabel pageLabel;
    private FlatPanel projectPagesContainer;

    private FlatButton addProjectButton;
    private FlatButton sortButton;
    private FlatButton exportButton;
    private FlatButton previousButton;
    private FlatButton nextButton;

    private ProjectListUI currentProjectList;
    private ProjectCreateUI projectCreateForm;

    public ProjectListView() {
        setupView();
    }

    /**
     * Displays the embedded project creation form.
     */
    public void showProjectCreateForm() {
        projectCreateForm.showForm();
    }

    /**
     * Retrieves user-entered data for a new project from the creation form.
     * @return A ProjectCreateRequest object representing the new project details.
     */
    public ProjectCreateRequest getProjectCreateRequest() {
        return projectCreateForm.getInitialProject();
    }

    /**
     * Adds a new project card to the currently displayed list.
     * If the current list is full, creates a new list and adds the project card to it.
     * @param card The ProjectCardUI representing the project to add.
     */
    public void addProjectToList(ProjectCardUI card) {
        if (currentProjectList.isFull()) {
            currentProjectList = createProjectList();
            addListToUI(currentProjectList, String.valueOf(currentProjectPage));
        }
        currentProjectList.addProject(card);
    }

    /**
     * Adds a new ProjectList to the UI and updates pagination.
     * @param list The ProjectListUI instance to add.
     * @param name The name associated with the list (page number).
     */
    private void addListToUI(ProjectListUI list, String name) {
        projectPagesContainer.add(list, name);
        projectPagesLayout.show(projectPagesContainer, name);

        currentTotalPage++;
        currentProjectPage = currentTotalPage;
        updatePageLabel();
        //CHANGED: removed repaint and revalidate
    }

    /**
     * Displays the next page of projects.
     */
    public void showNextPage() {
        if (hasNextPage()) {
            currentProjectPage++;
            projectPagesLayout.next(projectPagesContainer);
            updatePageLabel();
        }
    }

    /**
     * Displays the previous page of projects.
     */
    public void showPreviousPage() {
        if (hasPreviousPage()) {
            projectPagesLayout.previous(projectPagesContainer);
            currentProjectPage--;
            updatePageLabel();
        }
    }

    /**
     * Checks if there's a next page of projects available.
     * @return True if there's a next page, false otherwise.
     */
    public boolean hasNextPage() {
        return currentProjectPage < currentTotalPage && currentTotalPage != 1 && currentProjectPage >= 1;
    }

    /**
     * Checks if there's a previous page of projects available.
     * @return True if there's a previous page, false otherwise.
     */
    public boolean hasPreviousPage() {
        return currentProjectPage > 1 && currentTotalPage > 1 && currentProjectPage <= currentTotalPage;
    }

    /**
     * Updates the state of the navigation buttons (enabled/disabled) based on pagination.
     */
    public void updateButtonState() {
        previousButton.setEnabled(hasPreviousPage());
        nextButton.setEnabled(hasNextPage());
    }

    /**
     * Updates the page label (e.g., "Page 1 of 5").
     */
    private void updatePageLabel() {
        pageLabel.setText("Page " + currentProjectPage + " of " + currentTotalPage);
    }

    /**
     * <h3>Methods to add listeners to UI components</h3>
     * Handled by @ProjectController
     */
    public void addNextButtonListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }
    public void addPreviousButtonListener(ActionListener listener) {
        previousButton.addActionListener(listener);
    }
    public void addExportButtonListener(ActionListener listener) {
        exportButton.addActionListener(listener);
    }
    public void addSortButtonListener(ActionListener listener) {
        sortButton.addActionListener(listener);
    }
    public void addAddProjectButtonListener(ActionListener listener) {
        addProjectButton.addActionListener(listener);
    }
    public void addProjectCreationListener(ActionListener listener) {
        projectCreateForm.handleProjectCreation(listener);
    }

    /**
     * <h3>UI setup methods</h3>
     * <li>Setup view</li>
     * <li>Create header section</li>
     * <li>Create sorting section</li>
     * <li>Create footer section</li>
     * <li>Create page container</li>
     * <li>Create project list</li>
     */

    public void setupView() {
        this.setConstraints("insets 28, fill");

        FlatPanel mainContent = new FlatPanel()
                .setConstraints("insets 0, fillx", "", "[]0[]0[]0[]")
                .setLineBorder(1,1,1,1, 8)
                .applyFlatStyle();

        projectCreateForm = new ProjectCreateUI();
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

        FlatPanel header = new FlatPanel("insets 16 3% 16 3%, filly", "[]push[][]", "center");

        FlatLabel allProjectsLabel = FlatLabelFactory.createH1Label("All Projects");

        addProjectButton = FlatButtonFactory.createFilledButton("Add Project", AppIcons.ADD_ICON_SMALL);
        exportButton = FlatButtonFactory.createDefaultButton("Export", AppIcons.EXPORT_ICON_SMALL);
        sortButton = FlatButtonFactory.createDefaultButton("Sort", AppIcons.SORT_ICON_SMALL);

        header.add(allProjectsLabel, "gapbottom 5");
        header.add(sortButton, "gapbottom 5, hmin 32, hmax 32");
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
    public ProjectListUI createProjectList() {
        return new ProjectListUI();
    }

}
