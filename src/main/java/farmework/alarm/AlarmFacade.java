package farmework.alarm;

import farmework.alarm.service.AlarmService;
import farmework.model.FailureResult;

public final class AlarmFacade {

    public static void doAlarm(FailureResult failureResult) {
        new AlarmService().doAlarm(failureResult);
    }
}
