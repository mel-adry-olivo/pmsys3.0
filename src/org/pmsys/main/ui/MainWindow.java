package org.pmsys.main.ui;

import net.miginfocom.swing.MigLayout;
import org.pmsys.main.actions.Actions;
import org.pmsys.main.entities.Project;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.ui.components.*;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.components.constants.ColorConstants;
import org.pmsys.main.ui.components.constants.IconConstants;
import org.pmsys.main.ui.views.ProjectView;
import org.pmsys.main.ui.views.Views;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements CComponent{

    private final WindowNavBar menu;
    private final WindowHeader header;
    private final WindowContent content;

    public MainWindow( ) {
        content = new WindowContent();
        header = new WindowHeader();
        menu = new WindowNavBar();
        setupComponent();
    }

    public WindowContent getViewContent() {
        return content;
    }

    public WindowHeader getViewHeader() {
        return header;
    }

    public WindowNavBar getViewNavBar() {
        return menu;
    }

    public void setupComponent() {
        setLayout(new MigLayout("insets 0, fill", "[grow 0]0[]", "[]0[grow]"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("System");
        setSize(1280, 720);
        setLocationRelativeTo(null);

        add(menu, "cell 0 0 1 2, w 0%, grow");
        add(header, "cell 1 0, h 0%, grow");
        add(content, "cell 1 1, grow");
    }



    public static class WindowContent extends JLayeredPane {

        public WindowContent() {
            setupComponent();
        }

        private void setupComponent() {
            setLayout(new BorderLayout());
            setOpaque(true);
            setVisible(true);
        }

        @Override
        public Component add(Component comp) {
            add(comp, BorderLayout.CENTER);
            repaint();
            revalidate();
            return this;
        }
    }

    public static class WindowHeader extends CPanel {

        private CLabel viewIcon;
        private CLabel viewName;
        private CLabel separator;
        private CLabel projectName;

        private SearchBar searchBar;

        public WindowHeader() {
            setupComponent();
        }

        public void setupComponent() {
            setConstraints("insets 28 28 28 24 , filly", "[]8[]8[]8[]push[]8[]");
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode(ColorConstants.BORDER)));

            viewIcon = CLabelFactory.createIconLabel(IconConstants.DASHBOARD_ICON_SMALL);
            viewName = CLabelFactory.createDefaultLabel("View");
            separator = CLabelFactory.createLargeLabel("/", ColorConstants.DARK_GREY);
            projectName = CLabelFactory.createSemiBoldLabel("Project Name", ColorConstants.BLACK);

            searchBar = new SearchBar("Search for a project's name");
            UserAvatar userAvatar = new UserAvatar();

            add(viewIcon);
            add(viewName);
            add(separator);
            add(projectName);

            add(searchBar, "h 0%");
            add(userAvatar, "w 0%, h 0%");
        }

        public void updateViewName(Views views) {
            switch (views) {
                case DASHBOARD -> updateHeader(IconConstants.DASHBOARD_ICON_SMALL, "Dashboard");
                case PROJECT_LIST -> updateHeader(IconConstants.PROJECT_LIST_ICON_SMALL, "Project List");
                case PROJECT -> {
                    ProjectView currentProjectView = (ProjectView) Views.PROJECT.getComponent();
                    Project project = currentProjectView.getCurrentProject();
                    projectName.setText(project.getTitle());
                    separator.setVisible(true);
                    projectName.setVisible(true);
                }
            }
            searchBar.setText("");
        }

        // helper to the updateViewName method
        private void updateHeader(Icon icon, String name) {
            viewIcon.setIcon(icon);
            viewName.setText(name);
            separator.setVisible(false);
            projectName.setVisible(false);
        }
    }

    public static class WindowNavBar extends CPanel implements CComponent {

        private CButton dashboardButton;
        private CButton projectListButton;
        private CButton selectedButton; // current selected button

        public WindowNavBar() {
            setupComponent();
            projectListButton.setSelected(false);
            dashboardButton.setSelected(true);
            selectedButton = dashboardButton;
        }

        public CButton getSelectedButton() {
            return selectedButton;
        }

        public void setSelectedButton(CButton newSelectedButton) {
            if (selectedButton != null && selectedButton.equals(newSelectedButton)) {
                return; // If the same button is selected, do nothing
            }
            if (selectedButton != null) {
                selectedButton.setSelected(false); // Deselect the current button
            }
            selectedButton = newSelectedButton;
            if (selectedButton != null) {
                selectedButton.setSelected(true); // Select the new button
            }
        }

        public void setSelectedButton(String actionCommand) {
            CButton buttonToSelect = null;
            if (dashboardButton.getActionCommand().equals(actionCommand)) {
                buttonToSelect = dashboardButton;
            } else if (projectListButton.getActionCommand().equals(actionCommand)) {
                buttonToSelect = projectListButton;
            }
            if (buttonToSelect != null) {
                setSelectedButton(buttonToSelect);
            }
        }

        public void setupComponent() {
            setConstraints("flowy, fillx, insets 18", "center","[]45[]24[]");
            setMatteBorder(0, 0, 0, 1);

            CLabel logoIcon = CLabelFactory.createIconLabel(IconConstants.LOGO);
            dashboardButton = createMenuButton(IconConstants.DASHBOARD_ICON_MEDIUM, Views.DASHBOARD.name());
            projectListButton = createMenuButton(IconConstants.PROJECT_LIST_ICON_MEDIUM, Views.PROJECT_LIST.name());

            add(logoIcon, "w 36!, h 36!");
            add(dashboardButton, "w 36!, h 36!");
            add(projectListButton, "w 36!, h 36!");
        }

        private CButton createMenuButton(Icon icon, String actionCommand) {
            CButton button = CButtonFactory.createHoverableIconButton(icon);
            button.setActionCommand(actionCommand);
            button.addActionListener(e -> ActionManager.executeAction(Actions.VIEW_CHANGE, button, this));
            return button;
        }
    }
}
