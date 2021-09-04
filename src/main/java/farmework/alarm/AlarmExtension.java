package farmework.alarm;

import farmework.annotation.TripalAlarm;
import farmework.model.FailureResult;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AlarmExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        Method testMethod = context.getRequiredTestMethod();
        Class<?> testClass = context.getRequiredTestClass();
        String parameterTypes = Arrays.stream(testMethod.getParameterTypes()).map(Class::getName).collect(Collectors.joining(","));

        TripalAlarm talkAlarm = testMethod.getAnnotation(TripalAlarm.class);

        FailureResult failureResult = FailureResult.of(testClass.getName(), testMethod.getName(), parameterTypes, talkAlarm.token(), throwable);

        AlarmFacade.doAlarm(failureResult);
    }
}
