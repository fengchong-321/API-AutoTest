package farmework.format.observer;

import farmework.annotation.CaseDesc;
import farmework.exception.IllegalFormatException;
import farmework.format.FormatObserver;
import farmework.util.RequiredUtils;

import java.lang.reflect.Method;

public class CaseDescFormatObserver implements FormatObserver {

    @Override
    public void format(Method testMethod) {
        boolean isCaseDescSet = testMethod.isAnnotationPresent(CaseDesc.class);
        if (!isCaseDescSet) {
            throw new IllegalFormatException("@CaseDesc should be set, eg: @CaseDesc(desc = \"属于哪个需求，\", owner = \"zhangsan\")");
        }

        CaseDesc ds = testMethod.getAnnotation(CaseDesc.class);
        RequiredUtils.requireNotNullOrEmpty(ds.desc(), "CaseDesc 'desc' should not be null or empty");
        RequiredUtils.requireNotNullOrEmpty(ds.owner(), "CaseDesc 'owner' should not be null or empty");
    }
}
