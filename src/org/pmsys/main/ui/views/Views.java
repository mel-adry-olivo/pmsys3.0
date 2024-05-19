package org.pmsys.main.ui.views;

import org.pmsys.main.ui.components.base.FlatPanel;

public enum Views {
    DASHBOARD(new DashboardView()),
    PROJECT_LIST(new ProjectListView()),
    PROJECT(new ProjectView()),
    LOADING(new LoadingView());

    private final FlatPanel component;

    Views(FlatPanel viewComponent) {
        this.component = viewComponent;
    }

    public FlatPanel getComponent() {
        return component;
    }
}
