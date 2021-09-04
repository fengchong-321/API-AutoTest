package farmework.alarm.callback;

import farmework.alarm.AlarmFacade;
import farmework.model.FailureResult;

public class DefaultAlarmCallback implements AlarmCallback {

    @Override
    public void postExecutionFailure(FailureResult failureResult) {
        AlarmFacade.doAlarm(failureResult);
    }
}
