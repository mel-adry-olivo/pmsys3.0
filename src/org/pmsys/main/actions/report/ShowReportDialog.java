package org.pmsys.main.actions.report;

import org.pmsys.main.actions.SimpleAction;
import org.pmsys.main.ui.components.ReportDialog;
import org.pmsys.main.ui.components.base.CComponent;

import javax.swing.*;

public class ShowReportDialog implements SimpleAction {
    @Override
    public void execute(JComponent source, CComponent comp) {
        ReportDialog reportDialog = new ReportDialog();
        reportDialog.showForm();
    }
}
