package farmework.format.observer;

import farmework.annotation.TripalAlarm;
import farmework.format.FormatObserver;
import farmework.util.RequiredUtils;

import java.lang.reflect.Method;

public class DingTalkFormatObserver implements FormatObserver {

    @Override
    public void format(Method testMethod) {
        boolean isDingTalkAlarmSet = testMethod.isAnnotationPresent(TripalAlarm.class);
        if (!isDingTalkAlarmSet) {
            return;
        }

        TripalAlarm tripalAlarm = testMethod.getAnnotation(TripalAlarm.class);
        RequiredUtils.requireNotNullOrEmpty(tripalAlarm.token(), "TripalAlarm 'token' should not be null or empty");
    }
}
