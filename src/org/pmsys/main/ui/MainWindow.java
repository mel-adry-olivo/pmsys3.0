package org.pmsys.main.ui;

import net.miginfocom.swing.MigLayout;
import org.pmsys.constants.AppColors;
import org.pmsys.constants.AppIcons;
import org.pmsys.main.actions.Actions;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.managers.ViewManager;
import org.pmsys.main.ui.views.Views;
import org.pmsys.main.managers.SessionManager;
import org.pmsys.main.ui.components.base.FlatButton;
import org.pmsys.main.ui.components.base.FlatButtonFactory;
import org.pmsys.main.ui.components.base.FlatLabel;
import org.pmsys.main.ui.components.base.FlatPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainWindow extends JFrame{

    private final WindowMenu menu;
    private final WindowHeader header;
    private final WindowContent content;

    private final Map<Views, JComponent> views = new HashMap<>();

    public MainWindow( ) {
        content = new WindowContent();
        header = new WindowHeader();

        ViewManager.INSTANCE.setViewWindow(this); // was in this line because the menu needs the view manager

        menu = new WindowMenu();
        setupComponent();
    }

    public WindowContent getViewContent() {
        return content;
    }

    public WindowHeader getViewHeader() {
        return header;
    }

//    public void showView(Views views) {
//        JComponent viewComponent = this.views.get(views);
//        if (viewComponent != null) {
//            content.getLayout().show(content, views.name());
//            header.updateViewName(views);
//        }
//    }
//
//
//    public void addView(Views views, JComponent component) {
//        component.setPreferredSize(new Dimension(content.getWidth(), content.getHeight()));
//        content.add(component, views.name());
//        this.views.put(views, component);
//    }

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

//        public CardLayout getLayout() {
//            return (CardLayout) super.getLayout();
//        }
    }

    public static class WindowHeader extends FlatPanel {

        private FlatLabel viewIcon;
        private FlatLabel viewName;
        private FlatLabel separator;
        private FlatLabel projectName;
        private FlatLabel userName;

        private SearchBar searchBar;
        private JLabel userAvatar;

        public WindowHeader() {
            setupComponent();

        }

        private void setupComponent() {
            setConstraints("insets 24, filly", "[]8[]8[]8[]push[]0[]");
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#8F8F8F")));

            viewIcon = new FlatLabel(AppIcons.PROJECT_LIST_ICON_SMALL);
            viewName = new FlatLabel("View")
                    .setFontStyle(FlatLabel.DEFAULT)
                    .setForegroundColor(AppColors.DARK_GREY)
                    .applyFlatStyle();

            separator = new FlatLabel("/")
                    .setFontStyle(FlatLabel.LARGE)
                    .setForegroundColor(AppColors.DARK_GREY)
                    .applyFlatStyle();

            projectName = new FlatLabel("Project Name")
                    .setFontStyle(FlatLabel.SEMIBOLD)
                    .setForegroundColor(AppColors.BLACK)
                    .applyFlatStyle();

            userName = new FlatLabel("hi "+ SessionManager.INSTANCE.getUser().getUsername())
                    .setFontStyle(FlatLabel.SEMIBOLD)
                    .setForegroundColor(AppColors.BLACK)
                    .applyFlatStyle();

            searchBar = new SearchBar();
            userAvatar = new JLabel("");

            add(viewIcon);
            add(viewName, "");
            add(separator, "");
            add(projectName, "");


            add(userName);
            add(searchBar);
            add(userAvatar);
        }

        public void updateViewName(Views views) {
            switch (views) {
                case DASHBOARD -> {
                    viewIcon.setIcon(AppIcons.DASHBOARD_ICON_SMALL);
                    viewName.setText("Dashboard");
                    separator.setVisible(false);
                    projectName.setVisible(false);
                }
                case PROJECT_LIST -> {
                    viewIcon.setIcon(AppIcons.PROJECT_LIST_ICON_SMALL);
                    viewName.setText("Projects");
                    separator.setVisible(false);
                    projectName.setVisible(false);
                }
            }
        }

        private class SearchBar extends JPanel {

        }
    }

    public static class WindowMenu extends FlatPanel implements CComponent {


        private FlatButton dashboardButton;
        private FlatButton projectListButton;
        private FlatButton selectedButton;

        public WindowMenu() {


            setupComponent();

            // initial view
            projectListButton.setSelected(true);
            selectedButton = projectListButton;

            handleViewChange(projectListButton);

        }

        public FlatButton getSelectedButton() {
            return selectedButton;
        }

        public void setSelectedButton(FlatButton selectedButton) {
            if(this.selectedButton != null) {
                this.selectedButton = selectedButton;
            }
        }


        public void handleViewChange(FlatButton selectedButton) {
            String actionCommand = selectedButton.getActionCommand();
            Views selectedView = Views.valueOf(actionCommand);
            ViewManager.INSTANCE.showView(selectedView);
        }

        private void setupComponent() {
            setConstraints("flowy, fillx, insets 18", "center");
            setMatteBorder(0, 0, 0, 1);

            FlatLabel logoIcon = new FlatLabel(AppIcons.LOGO);

            dashboardButton = FlatButtonFactory.createHoverableIconButton(AppIcons.DASHBOARD_ICON_MEDIUM);
            dashboardButton.setActionCommand(Views.DASHBOARD.name());
            dashboardButton.addActionListener(e -> ActionManager.executeAction(Actions.VIEW_CHANGE, dashboardButton, this));

            projectListButton = FlatButtonFactory.createHoverableIconButton(AppIcons.PROJECT_LIST_ICON_MEDIUM);
            projectListButton.setActionCommand(Views.PROJECT_LIST.name());
            projectListButton.addActionListener(e -> ActionManager.executeAction(Actions.VIEW_CHANGE, projectListButton, this));


            add(logoIcon, "wmin 36, wmax 36, hmin 36, hmax 36");
            add(dashboardButton, "gaptop 45, wmin 36, wmax 36, hmin 36, hmax 36");
            add(projectListButton, "gaptop 24, wmin 36, wmax 36, hmin 36, hmax 36");
        }


    }
}
