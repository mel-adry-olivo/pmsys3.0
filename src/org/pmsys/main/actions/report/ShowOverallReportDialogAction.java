package org.pmsys.main.actions.report;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.actions.SimpleAction;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.managers.ProjectManager;
import org.pmsys.main.ui.components.ReportDialog;
import org.pmsys.main.ui.components.base.CComponent;
import org.pmsys.main.util.ReportFormatter;

import javax.swing.*;

public class ShowOverallReportDialogAction implements SimpleAction {
    @Override
    public void execute(JComponent source, CComponent comp) {
        ReportDialog reportDialog = new ReportDialog("Overall Report");
        reportDialog.handleSaveButton((e) -> ActionManager.executeAction(Actions.SAVE_OVERALL_REPORT, null, reportDialog));
        reportDialog.clearReportContent();
        String content = ReportFormatter.formatOverall(ProjectManager.INSTANCE.getAlLProjects());
        reportDialog.setReportContent(content);
        reportDialog.goToTop();
        reportDialog.showForm();
    }
}
