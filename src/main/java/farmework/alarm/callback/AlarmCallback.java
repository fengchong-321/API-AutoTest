package farmework.alarm.callback;


import farmework.model.FailureResult;

public interface AlarmCallback {

    void postExecutionFailure(FailureResult failureResult);
}
