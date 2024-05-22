package org.pmsys.main.ui;

import net.miginfocom.swing.MigLayout;
import org.pmsys.main.actions.Actions;
import org.pmsys.main.entities.Project;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.managers.ViewManager;
import org.pmsys.main.ui.components.SearchBar;
import org.pmsys.main.ui.components.UserAvatar;
import org.pmsys.main.ui.views.ProjectView;
import org.pmsys.main.ui.views.Views;
import org.pmsys.main.ui.components.base.CButton;
import org.pmsys.main.ui.components.base.CButtonFactory;
import org.pmsys.main.ui.components.base.CLabel;
import org.pmsys.main.ui.components.base.CPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{

    private final WindowNavBar menu;
    private final WindowHeader header;
    private final WindowContent content;

    public MainWindow( ) {
        content = new WindowContent();
        header = new WindowHeader();
        ViewManager.INSTANCE.setViewWindow(this); // was in this line because the navbar needs the view manager
        menu = new WindowNavBar();
        setupComponent();
    }

    public WindowContent getViewContent() {
        return content;
    }

    public WindowHeader getViewHeader() {
        return header;
    }

    private void setupComponent() {
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
        private UserAvatar userAvatar;

        public WindowHeader() {
            setupComponent();

        }

        private void setupComponent() {
            setConstraints("insets 28 28 28 24 , filly", "[]8[]8[]8[]push[]8[]");
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode(ColorConstants.BORDER)));

            viewIcon = new CLabel(IconConstants.PROJECT_LIST_ICON_SMALL);
            viewName = new CLabel("View")
                    .setFontStyle(CLabel.DEFAULT)
                    .setForegroundColor(ColorConstants.DARK_GREY)
                    .applyFlatStyle();

            separator = new CLabel("/")
                    .setFontStyle(CLabel.LARGE)
                    .setForegroundColor(ColorConstants.DARK_GREY)
                    .applyFlatStyle();

            projectName = new CLabel("Project Name")
                    .setFontStyle(CLabel.SEMIBOLD)
                    .setForegroundColor(ColorConstants.BLACK)
                    .applyFlatStyle();

            searchBar = new SearchBar("Search for a project's name");
            userAvatar = new UserAvatar();

            add(viewIcon);
            add(viewName, "");
            add(separator, "");
            add(projectName, "");

            add(searchBar, "h 0%");
            add(userAvatar, "w 0%, h 0%");
        }

        public void updateViewName(Views views) {
            switch (views) {
                case DASHBOARD -> {
                    viewIcon.setIcon(IconConstants.DASHBOARD_ICON_SMALL);
                    viewName.setText("Dashboard");
                    separator.setVisible(false);
                    projectName.setVisible(false);
                }
                case PROJECT_LIST -> {
                    viewIcon.setIcon(IconConstants.PROJECT_LIST_ICON_SMALL);
                    viewName.setText("Projects");
                    separator.setVisible(false);
                    projectName.setVisible(false);
                }
                case PROJECT -> {
                    Project project = ((ProjectView )Views.PROJECT.getComponent()).getCurrentProject();
                    projectName.setText(project.getTitle());
                    separator.setVisible(true);
                    projectName.setVisible(true);
                }
            }
            searchBar.setText("");
        }
    }

    public static class WindowNavBar extends CPanel implements CComponent {

        private CButton dashboardButton;
        private CButton projectListButton;
        private CButton selectedButton;

        public WindowNavBar() {

            setupComponent();

            projectListButton.setSelected(true);
            selectedButton = projectListButton;

            handleViewChange(projectListButton);

        }

        public CButton getSelectedButton() {
            return selectedButton;
        }

        public void setSelectedButton(CButton selectedButton) {
            if(this.selectedButton != null) {
                this.selectedButton = selectedButton;
            }
        }


        public void handleViewChange(CButton selectedButton) {
            String actionCommand = selectedButton.getActionCommand();
            Views selectedView = Views.valueOf(actionCommand);
            ViewManager.INSTANCE.showView(selectedView);
        }

        private void setupComponent() {
            setConstraints("flowy, fillx, insets 18", "center");
            setMatteBorder(0, 0, 0, 1);

            CLabel logoIcon = new CLabel(IconConstants.LOGO);

            dashboardButton = CButtonFactory.createHoverableIconButton(IconConstants.DASHBOARD_ICON_MEDIUM);
            dashboardButton.setActionCommand(Views.DASHBOARD.name());
            dashboardButton.addActionListener(e -> ActionManager.executeAction(Actions.VIEW_CHANGE, dashboardButton, this));

            projectListButton = CButtonFactory.createHoverableIconButton(IconConstants.PROJECT_LIST_ICON_MEDIUM);
            projectListButton.setActionCommand(Views.PROJECT_LIST.name());
            projectListButton.addActionListener(e -> ActionManager.executeAction(Actions.VIEW_CHANGE, projectListButton, this));


            add(logoIcon, "wmin 36, wmax 36, hmin 36, hmax 36");
            add(dashboardButton, "gaptop 45, wmin 36, wmax 36, hmin 36, hmax 36");
            add(projectListButton, "gaptop 24, wmin 36, wmax 36, hmin 36, hmax 36");
        }


    }
}
