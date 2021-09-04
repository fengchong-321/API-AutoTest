package farmework.format.observer;

import farmework.annotation.CheckPoint;
import farmework.exception.IllegalFormatException;
import farmework.format.FormatObserver;
import farmework.util.RequiredUtils;

import java.lang.reflect.Method;

public class CheckPointFormatObserver implements FormatObserver {

    @Override
    public void format(Method testMethod) {
        CheckPoint[] checkPoints = testMethod.getAnnotationsByType(CheckPoint.class);
        if (checkPoints.length == 0) {
            throw new IllegalFormatException("@CheckPoint should be set, eg: @CheckPoint(\"检查点\")");
        }

        for (CheckPoint checkPoint : checkPoints) {
            RequiredUtils.requireNotNullOrEmpty(checkPoint.value(), "CheckPoint 'value' should not be null or empty");
        }
    }
}
