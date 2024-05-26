package org.pmsys.main.services;

public class ReportService extends FileService {

    public void saveOverallReport(String content) {
        Utils.CREATE(getOverallReportFileOfCurrentUser());
        Utils.OVERWRITE(getOverallReportFileOfCurrentUser(), content);
    }

    public void saveIndividualReport(String content) {
        Utils.CREATE(getIndividualReportFileOfCurrentUser());
        Utils.OVERWRITE(getIndividualReportFileOfCurrentUser(), content);
    }
}
