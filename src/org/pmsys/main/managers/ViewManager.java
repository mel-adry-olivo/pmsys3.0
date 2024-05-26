package org.pmsys.main.managers;

import org.pmsys.main.ui.MainWindow;
import org.pmsys.main.ui.components.base.CPanel;
import org.pmsys.main.ui.views.Views;

import java.awt.*;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum ViewManager {

    INSTANCE;

    private final Map<Views, CPanel> views = new EnumMap<>(Views.class);
    private final Set<Views> addedViews = new HashSet<>();
    private final CardLayout cardLayout = new CardLayout();

    private MainWindow.WindowContent viewContent;
    private MainWindow.WindowHeader viewHeader;
    private MainWindow.WindowNavBar viewNavBar;

    private Views currentView;
    private Views previousView;

    public void clearViews() {
        views.clear();
        addedViews.clear();
        currentView = null;

        if (viewContent != null) {
            viewContent.removeAll();
        }
    }

    public Views getPreviousView() {
        return previousView;
    }

    public void setViewWindow(MainWindow viewWindow) {
        viewContent = viewWindow.getViewContent();
        viewHeader = viewWindow.getViewHeader();
        viewNavBar = viewWindow.getViewNavBar();
        viewContent.setLayout(cardLayout);
    }

    public void showView(Views view) {
        if (view == null || currentView == view) return;

        CPanel viewComponent = views.get(view);
        if (viewComponent != null) {
            if (addedViews.add(view)) {
                viewContent.add(viewComponent, view.name());
            }
            cardLayout.show(viewContent, view.name());
            viewComponent.grabFocus();
            viewHeader.updateViewName(view);
            viewNavBar.setSelectedButton(view.name());
            previousView = currentView;
            currentView = view;
        }
    }

    public CPanel getViewComponent(Views view) {
        return views.get(view);
    }

    public void registerView(Views view) {
        views.put(view, view.getComponent());
    }

    public void loadViews() {
        for (Views view : Views.values()) {
            registerView(view);
        }
    }
}
