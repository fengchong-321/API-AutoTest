package farmework.engine.filter;

import farmework.annotation.CaseGroup;
import farmework.annotation.CaseSelector;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.engine.FilterResult;

import java.lang.reflect.Method;

public class CaseGroupDiscoveryFilter extends AbstractDiscoveryFilter {

    public CaseGroupDiscoveryFilter(CaseSelector selector) {
        super(selector);
    }

    @Override
    protected boolean preFilter(CaseSelector selector) {
        return StringUtils.isNotBlank(selector.team()) && StringUtils.isNotBlank(selector.group());
    }

    @Override
    protected FilterResult onApply(TestMethodTestDescriptor descriptor) {
        Method testMethod = descriptor.getTestMethod();

        boolean isCaseGroupSet = testMethod.isAnnotationPresent(CaseGroup.class);
        if (!isCaseGroupSet) {
            return FilterResult.includedIf(false);
        }

        CaseGroup caseGroup = testMethod.getAnnotation(CaseGroup.class);
        if (selector.team().equals(caseGroup.team()) && selector.group().equals(caseGroup.group())) {
            return FilterResult.includedIf(true);
        }

        return FilterResult.includedIf(false);
    }

}
