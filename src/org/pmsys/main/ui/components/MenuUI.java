package org.pmsys.main.ui.components;

import net.miginfocom.swing.MigLayout;
import org.pmsys.constants.AppColors;
import org.pmsys.constants.AppIcons;
import org.pmsys.main.ui.components.base.FlatButton;
import org.pmsys.main.ui.components.base.FlatLabel;
import org.pmsys.main.ui.components.base.FlatPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// navigation menu where buttons to go to different views will go
public class MenuUI extends FlatPanel{

    private final MigLayout layout = new MigLayout("flowy, fillx, insets 18", "center");
    private final HeaderUI header;
    private final FlatPanel mainContentArea;

    private FlatLabel logoIcon;
    private FlatButton dashboardButton;
    private FlatButton projectListButton;
    private FlatButton selectedButton;

    public MenuUI(HeaderUI header, FlatPanel mainContentArea) {
        this.header = header;
        this.mainContentArea = mainContentArea;

        setupComponent();
        attachListeners();

        // initial view
        projectListButton.setSelected(true);
        selectedButton = projectListButton;
        handleViewChange(projectListButton);
        handleHeaderChange(projectListButton);
    }

    private void attachListeners() {
        dashboardButton.addActionListener(this::handleButtonClick);
        projectListButton.addActionListener(this::handleButtonClick);
    }

    private void handleViewChange(JButton selectedButton) {
        CardLayout layout = (CardLayout) mainContentArea.getLayout();
        switch (selectedButton.getName()) {
            case "dashboardButton" -> layout.show(mainContentArea, "dashboardView");
            case "projectListButton" -> layout.show(mainContentArea, "projectListView");
        }
    }

    public void handleHeaderChange(JButton selectedButton) {
        switch (selectedButton.getName()) {
            case "dashboardButton" -> header.updateViewName("dashboardView");
            case "projectListButton" -> header.updateViewName("projectListView");
        }
    }

    private void setupComponent() {
        setConstraints("flowy, fillx, insets 18", "center");
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode("#8F8F8F")));

        logoIcon = new FlatLabel(AppIcons.LOGO);
        logoIcon.setName("logoIcon");

        dashboardButton = new FlatButton(AppIcons.DASHBOARD_ICON_MEDIUM);
        dashboardButton.setName("dashboardButton");

        projectListButton = new FlatButton(AppIcons.PROJECT_LIST_ICON_MEDIUM);
        projectListButton.setName("projectListButton");

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
        handleHeaderChange(selectedButton);
    }

}
