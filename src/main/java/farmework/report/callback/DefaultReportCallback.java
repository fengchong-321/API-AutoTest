package farmework.report.callback;

import com.google.common.collect.Maps;
import farmework.http.HttpFacade;
import farmework.model.SummaryResult;
import farmework.template.TemplateFacade;

import java.util.Map;
import java.util.stream.Collectors;

public class DefaultReportCallback implements ReportCallback {

    @Override
    public void postExecutionSummary(SummaryResult summaryResult) {
        Map<String, Object> templateMapping = Maps.newHashMap();
        templateMapping.put("successful_count", summaryResult.getSucceededCount());
        templateMapping.put("failed_count", summaryResult.getFailedCount());
        templateMapping.put("total_fount", summaryResult.getFoundCount());
        templateMapping.put("start_time", summaryResult.getTimeStarted());
        templateMapping.put("end_time", summaryResult.getTimeFinished());
        templateMapping.put("failure_reason", summaryResult.getFailureInfoList().stream().map(f -> f.getThrowable().getMessage()).collect(Collectors.joining(";")));

        String reportText = TemplateFacade.replaceTemplate("default_report_template", templateMapping);

        Map<String, Object> param = Maps.newHashMap();
        param.put("msgtype", "text");

        Map<String, Object> data = Maps.newHashMap();
        data.put("content", reportText);
        param.put("text", data);
        String response = HttpFacade.doPostJson(DING_TALK_ROOT_URL + summaryResult.getToken(), param);
    }

    private static final String DING_TALK_ROOT_URL = "https://api.Tripal.com/robot/send?access_token=";
}
