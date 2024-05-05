package org.pmsys.main.ui.components;

import org.pmsys.constants.AppColors;
import org.pmsys.constants.AppIcons;
import org.pmsys.main.manager.SessionManager;
import org.pmsys.main.ui.components.base.FlatLabel;
import org.pmsys.main.ui.components.base.FlatPanel;

import javax.swing.*;
import java.awt.*;

// TODO - header where user avatar, searchbar, and view info will go
public class HeaderUI extends FlatPanel {

    private FlatLabel viewIcon;
    private FlatLabel viewName;
    private FlatLabel separator;
    private FlatLabel projectName;
    private FlatLabel userName;

    private SearchBar searchBar;
    private JLabel userAvatar;

    public HeaderUI() {
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
                .setFontStyle(FlatLabel.DEFAULT)
                .setForegroundColor(AppColors.DARK_GREY)
                .applyFlatStyle();

        projectName = new FlatLabel("Project Name")
                .setFontStyle(FlatLabel.SEMIBOLD)
                .setForegroundColor(AppColors.BLACK)
                .applyFlatStyle();

        userName = new FlatLabel("hi "+SessionManager.getInstance().getCurrentUser().getUsername())
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

    public void updateViewName(String view) {
        switch (view) {
            case "dashboardView" -> {
                viewIcon.setIcon(AppIcons.DASHBOARD_ICON_SMALL);
                viewName.setText("Dashboard");
                separator.setVisible(false);
                projectName.setVisible(false);
            }
            case "projectListView" -> {
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
