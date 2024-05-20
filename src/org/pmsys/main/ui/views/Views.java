package org.pmsys.main.ui.views;

import org.pmsys.main.ui.components.base.CPanel;

public enum Views {
    DASHBOARD(new DashboardView()),
    PROJECT_LIST(new ProjectListView()),
    PROJECT(new ProjectView()),
    LOADING(new LoadingView());

    private final CPanel component;

    Views(CPanel viewComponent) {
        this.component = viewComponent;
    }

    public CPanel getComponent() {
        return component;
    }
}
