package org.pmsys.main.ui;

import net.miginfocom.swing.MigLayout;
import org.pmsys.constants.AppColors;
import org.pmsys.constants.AppIcons;
import org.pmsys.constants.View;
import org.pmsys.main.manager.SessionManager;
import org.pmsys.main.manager.ViewManager;
import org.pmsys.main.ui.components.base.FlatButton;
import org.pmsys.main.ui.components.base.FlatButtonFactory;
import org.pmsys.main.ui.components.base.FlatLabel;
import org.pmsys.main.ui.components.base.FlatPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class MainWindow extends JFrame implements ViewManager {

    private final WindowMenu menu;
    private final WindowHeader header;
    private final WindowContent content;

    private final Map<View, JComponent> views = new HashMap<>();

    public MainWindow( ) {
        content = new WindowContent();
        header = new WindowHeader();
        menu = new WindowMenu(this);

        setupComponent();

    }

    @Override
    public void showView(View view) {
        JComponent viewComponent = views.get(view);
        if (viewComponent != null) {
            content.getLayout().show(content, view.name());
            header.updateViewName(view);
        }
    }

    @Override
    public void addView(View view, JComponent component) {
        component.setPreferredSize(new Dimension(content.getWidth(), content.getHeight()));
        content.add(component, view.name());
        views.put(view, component);
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

    private static class WindowContent extends JLayeredPane {

        public WindowContent() {
            setupComponent();
        }

        private void setupComponent() {
            setLayout(new CardLayout());
            setOpaque(true);
            setVisible(true);
        }

        public CardLayout getLayout() {
            return (CardLayout) super.getLayout();
        }
    }

    private static class WindowHeader extends FlatPanel {

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

            userName = new FlatLabel("hi "+ SessionManager.getInstance().getCurrentUser().getUsername())
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

        public void updateViewName(View view) {
            switch (view) {
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

        public void handleOpenedProject(String projectName) {
            separator.setVisible(true);
            this.projectName.setVisible(true);
            this.projectName.setText(projectName);
        }

        private class SearchBar extends JPanel {

        }
    }

    private static class WindowMenu extends FlatPanel{

        private final MainWindow mainWindow;

        private FlatButton dashboardButton;
        private FlatButton projectListButton;
        private FlatButton selectedButton;

        public WindowMenu(MainWindow mainWindow) {
            this.mainWindow = mainWindow;

            setupComponent();
            attachListeners();

            // initial view
            projectListButton.setSelected(true);
            selectedButton = projectListButton;

            handleViewChange(projectListButton);

        }

        private void attachListeners() {
            dashboardButton.addActionListener(this::handleButtonClick);
            projectListButton.addActionListener(this::handleButtonClick);
        }

        private void handleViewChange(JButton selectedButton) {

            String actionCommand = selectedButton.getActionCommand();
            View selectedView = View.valueOf(actionCommand);

            mainWindow.showView(selectedView);

        }

        private void setupComponent() {
            setConstraints("flowy, fillx, insets 18", "center");
            setMatteBorder(0, 0, 0, 1);

            FlatLabel logoIcon = new FlatLabel(AppIcons.LOGO);

            dashboardButton = FlatButtonFactory.createHoverableIconButton(AppIcons.DASHBOARD_ICON_MEDIUM);
            projectListButton = FlatButtonFactory.createHoverableIconButton(AppIcons.PROJECT_LIST_ICON_MEDIUM);

            dashboardButton.setActionCommand(View.DASHBOARD.name());
            projectListButton.setActionCommand(View.PROJECT_LIST.name());

            add(logoIcon, "wmin 36, wmax 36, hmin 36, hmax 36");
            add(dashboardButton, "gaptop 45, wmin 36, wmax 36, hmin 36, hmax 36");
            add(projectListButton, "gaptop 24, wmin 36, wmax 36, hmin 36, hmax 36");
        }

        private void handleButtonClick(ActionEvent e) {
            FlatButton clickedButton = (FlatButton) e.getSource();

            if (selectedButton != clickedButton && selectedButton != null) {
                selectedButton.setSelected(false);
                selectedButton.setBackground(new Color(255,255,255));
            }

            selectedButton = clickedButton;
            selectedButton.setPressedBackgroundColor(AppColors.PRESSED_GREY);
            selectedButton.setSelected(true);
            selectedButton.setBackground(new Color(80,80,80));

            handleViewChange(selectedButton);
        }

    }
}
