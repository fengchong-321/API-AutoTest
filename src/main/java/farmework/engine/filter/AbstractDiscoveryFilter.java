package farmework.engine.filter;

import farmework.annotation.CaseSelector;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.engine.FilterResult;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.launcher.PostDiscoveryFilter;

public abstract class AbstractDiscoveryFilter implements PostDiscoveryFilter {

    protected CaseSelector selector;

    public AbstractDiscoveryFilter(CaseSelector selector) {
        this.selector = selector;
    }

    protected abstract boolean preFilter(CaseSelector selector);

    protected abstract FilterResult onApply(TestMethodTestDescriptor descriptor);

    @Override
    public FilterResult apply(TestDescriptor testDescriptor) {
        if (!preFilter(this.selector)) {
            return FilterResult.includedIf(true);
        }

        if (!(testDescriptor instanceof TestMethodTestDescriptor)) {
            return FilterResult.includedIf(false);
        }

        return onApply((TestMethodTestDescriptor) testDescriptor);
    }
}
