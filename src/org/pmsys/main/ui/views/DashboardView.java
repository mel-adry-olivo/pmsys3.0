package org.pmsys.main.ui.views;


import org.pmsys.main.ui.components.base.CPanel;


public class DashboardView extends CPanel {

    public DashboardView() {
        setupView();
    }

    public void setupView() {
        setConstraints("insets 28, fill");

        CPanel mainContent = new CPanel()
                .setConstraints("insets 0, fillx", "", "[]0[]0[]0[]")
                .setLineBorder(1,1,1,1, 8);

        add(mainContent, "grow");
    }

}
