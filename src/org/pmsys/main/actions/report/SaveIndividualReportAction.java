package org.pmsys.main.actions.report;

import org.pmsys.main.actions.SimpleAction;
import org.pmsys.main.managers.ServiceManager;
import org.pmsys.main.services.ReportService;
import org.pmsys.main.services.Services;
import org.pmsys.main.ui.components.ReportDialog;
import org.pmsys.main.ui.components.base.CComponent;
import org.pmsys.main.ui.utils.MessageUtils;

import javax.swing.*;

public class SaveIndividualReportAction implements SimpleAction {
    @Override
    public void execute(JComponent source, CComponent comp) {
        ReportDialog reportDialog = (ReportDialog) comp;
        ReportService reportService = (ReportService) ServiceManager.getService(Services.REPORT);
        String content = reportDialog.getContent();
        reportService.saveIndividualReport(content);

        MessageUtils.SUCCESS("Individual report saved in /report/individual");
        reportDialog.dispose();
    }
}
