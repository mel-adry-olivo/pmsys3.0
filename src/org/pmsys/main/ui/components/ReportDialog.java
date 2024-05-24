package org.pmsys.main.ui.components;

import org.pmsys.main.ui.components.base.CDialog;

public class ReportDialog extends CDialog {

    public ReportDialog() {
        setupComponent();
    }

    private void setupComponent() {
        setFormSize(500, 500);
        setConstraints("insets 8% 10% 8% 10%, fill");
    }
}
