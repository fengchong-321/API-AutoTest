package farmework.gen.filter;

import com.google.common.collect.Lists;
import farmework.gen.model.GenContext;

import java.util.List;

public class CreateCodeFilterChain {
    private List<ICreateCodeFilter> filters;

    private CreateCodeFilterChain() {
        this.filters = Lists.newArrayList(new PackageClassCodeFilter());
    }

    private static class ClassHolder {
        private static final CreateCodeFilterChain INSTANCE = new CreateCodeFilterChain();
    }

    public static CreateCodeFilterChain of() {
        return ClassHolder.INSTANCE;
    }

    public void doFilter(GenContext context) {
        for (ICreateCodeFilter filter : this.filters) {
            filter.doFilter(context);
        }
    }
}
