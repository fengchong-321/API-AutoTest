package farmework.report.callback;


import farmework.model.SummaryResult;

public interface ReportCallback {

    void postExecutionSummary(SummaryResult summaryResult);
}
