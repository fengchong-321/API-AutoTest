package farmework.engine.filter;

import farmework.annotation.CaseSelector;
import farmework.annotation.CaseTag;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.engine.FilterResult;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CaseTagDiscoveryFilter extends AbstractDiscoveryFilter {

    public CaseTagDiscoveryFilter(CaseSelector selector) {
        super(selector);
    }

    @Override
    protected boolean preFilter(CaseSelector selector) {
        return StringUtils.isNotBlank(selector.key()) && StringUtils.isNotBlank(selector.val());
    }

    @Override
    protected FilterResult onApply(TestMethodTestDescriptor descriptor) {
        Method testMethod = descriptor.getTestMethod();
        CaseTag[] tags = testMethod.getAnnotationsByType(CaseTag.class);
        long selectedTagCount = Arrays.stream(tags)
                .filter(tag -> tag.key().equals(selector.key()) &&
                        tag.val().equals(selector.val()))
                .count();

        return selectedTagCount > 0 ? FilterResult.includedIf(true) : FilterResult.includedIf(false);
    }
}
