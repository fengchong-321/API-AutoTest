package farmework.alarm.service;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import farmework.annotation.CaseDesc;
import farmework.annotation.CaseTitle;
import farmework.annotation.CheckPoint;
import farmework.http.HttpFacade;
import farmework.model.FailureResult;
import farmework.template.TemplateFacade;
import farmework.util.ReflectUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AlarmService {

    public void doAlarm(FailureResult failureResult) {
        String title = null;
        String desc = null;
        String owner = null;
        List<String> checkPoints = null;
        String caseId = failureResult.getClassName() + "#" + failureResult.getMethodName();
        String reason = failureResult.getThrowable().getMessage();

        Method testMethod = ReflectUtils.getMethod(failureResult.getClassName(), failureResult.getMethodName());
        boolean isCaseTitleSet = testMethod.isAnnotationPresent(CaseTitle.class);
        if (isCaseTitleSet) {
            CaseTitle caseTitle = testMethod.getAnnotation(CaseTitle.class);
            title = caseTitle.value();
        }

        boolean isCaseDescSet = testMethod.isAnnotationPresent(CaseDesc.class);
        if (isCaseDescSet) {
            CaseDesc caseDesc = testMethod.getAnnotation(CaseDesc.class);
            desc = caseDesc.desc();
            owner = caseDesc.owner();
        }

        CheckPoint[] ckps = testMethod.getAnnotationsByType(CheckPoint.class);
        checkPoints = Arrays.stream(ckps).map(CheckPoint::value).collect(Collectors.toList());

        Map<String, Object> alarmTemplateMapping = Maps.newHashMap();
        alarmTemplateMapping.put("case_title", title);
        alarmTemplateMapping.put("case_desc", desc);
        alarmTemplateMapping.put("case_id", caseId);
        alarmTemplateMapping.put("case_owner", owner);
        alarmTemplateMapping.put("case_ckps", Joiner.on(",").join(checkPoints));
        alarmTemplateMapping.put("failure_reason", reason);

        String text = TemplateFacade.replaceTemplate("default_alarm_template", alarmTemplateMapping);

//        {"msgtype": "text","text": {"content": "报警报告"}}
        Map<String, Object> param = Maps.newHashMap();
        param.put("msgtype", "text");

        Map<String, Object> data = Maps.newHashMap();
        data.put("content", text);
        param.put("text", data);
        String response = HttpFacade.doPostJson(TRIPAL_ROOT_URL + failureResult.getToken(), param);
    }

    private static final String TRIPAL_ROOT_URL = "https://api.tripal.com/robot/send?access_token=";
}
