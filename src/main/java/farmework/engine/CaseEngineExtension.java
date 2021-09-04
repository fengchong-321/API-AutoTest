package farmework.engine;

import farmework.annotation.CaseSelector;
import farmework.annotation.ReportConfig;
import farmework.annotation.TripalAlarm;
import farmework.engine.filter.CaseGroupDiscoveryFilter;
import farmework.engine.filter.CaseTagDiscoveryFilter;
import farmework.engine.listener.FailureListener;
import farmework.model.FailureInfo;
import farmework.model.SummaryResult;
import farmework.util.ReflectUtils;
import farmework.util.RequiredUtils;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.engine.TestSource;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.engine.support.descriptor.MethodSource;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class CaseEngineExtension implements BeforeTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        Method testMethod = context.getRequiredTestMethod();
        CaseSelector selector = invalidedSelector(testMethod.getAnnotation(CaseSelector.class));

        // 构造发现用例的请求实体
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                // 选择器,基于包名进行选择
                .selectors(DiscoverySelectors.selectPackage(selector.scanPackage()))

                // 过滤器
                .filters(new CaseTagDiscoveryFilter(selector),
                        new CaseGroupDiscoveryFilter(selector))
                .configurationParameter("junit.jupiter.execution.parallel.enabled", "true")
                .configurationParameter("junit.jupiter.execution.parallel.mode.default", "concurrent")
                .configurationParameter("junit.jupiter.execution.parallel.mode.classes.default", "concurrent")
                .build();

        // 信息结果收集的监听器
        SummaryGeneratingListener summaryGeneratingListener = new SummaryGeneratingListener();

        // 处理失败的监听器: 自定义的
        boolean isAlarmSet = testMethod.isAnnotationPresent(TripalAlarm.class);
        if (isAlarmSet) {
            TripalAlarm tripalAlarm = testMethod.getAnnotation(TripalAlarm.class);
            FailureListener failureListener = new FailureListener(tripalAlarm.token(), tripalAlarm.callback());
            LauncherFactory.create().execute(request, summaryGeneratingListener, failureListener);
        } else {
            LauncherFactory.create().execute(request, summaryGeneratingListener);
        }

        boolean isReportConfigSet = testMethod.isAnnotationPresent(ReportConfig.class);
        if (isReportConfigSet) {
            ReportConfig reportConfig = testMethod.getAnnotation(ReportConfig.class);
            SummaryResult summaryResult = transToSummaryResult(summaryGeneratingListener.getSummary());
            summaryResult.setToken(reportConfig.token());
            ReflectUtils.newInstance(reportConfig.callback()).postExecutionSummary(summaryResult);
        }

        // 插入MySQL
        // SummaryResultDao,insert(summaryResult)
    }

    private SummaryResult transToSummaryResult(TestExecutionSummary summary) {
        SummaryResult summaryResult = new SummaryResult();
        summaryResult.setAbortedCount(summary.getTestsAbortedCount());
        summaryResult.setFailedCount(summary.getTestsFailedCount());
        summaryResult.setFailureInfoList(parseFailureInfoList(summary.getFailures()));
        summaryResult.setFoundCount(summary.getTestsFoundCount());
        summaryResult.setSkippedCount(summary.getTestsSkippedCount());
        summaryResult.setStartedCount(summary.getTestsStartedCount());
        summaryResult.setSucceededCount(summary.getTestsSucceededCount());
        summaryResult.setTimeFinished(summary.getTimeFinished());
        summaryResult.setTimeStarted(summary.getTimeStarted());
        summaryResult.setTotalFailureCount(summary.getTotalFailureCount());

        return summaryResult;
    }

    private List<FailureInfo> parseFailureInfoList(List<TestExecutionSummary.Failure> failures) {
        return failures.stream().map(failure -> {
            TestIdentifier testIdentifier = failure.getTestIdentifier();
            Optional<TestSource> optionalTestSource = testIdentifier.getSource();
            TestSource testSource = optionalTestSource.get();
            MethodSource methodSource = (MethodSource) testSource;

            FailureInfo failureInfo = new FailureInfo();
            failureInfo.setClassName(methodSource.getClassName());
            failureInfo.setMethodName(methodSource.getMethodName());
            failureInfo.setParameterTypes(methodSource.getMethodParameterTypes());
            failureInfo.setThrowable(failure.getException());
            return failureInfo;
        }).collect(Collectors.toList());
    }

    private CaseSelector invalidedSelector(CaseSelector selector) {
        RequiredUtils.requiredNotNull(selector, "case selector should not be null.");
        RequiredUtils.requireNotNullOrEmpty(selector.scanPackage(), "scan package should not be null or empty.");

        // TODO 判断,key 和 val 可以不写，但是如果写了，就一定都不能为空
        selector.key();
        selector.val();

        return selector;
    }
}
