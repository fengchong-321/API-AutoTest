package farmework.gen.chain;


import farmework.gen.model.GenContext;
import farmework.util.RequiredUtils;

import java.util.Objects;

public abstract class AbstractGenHandler {

    private AbstractGenHandler nextHandler;

    protected abstract boolean preGen(GenContext context);

    protected abstract String onGen(GenContext context);

    public String doGen(GenContext context) {
        RequiredUtils.requiredNotNull(context, "gen context should not be null.");
        if (preGen(context)) {
            return onGen(context);
        }

        if (!Objects.isNull(this.nextHandler)) {
            return this.nextHandler.doGen(context);
        }

        throw new IllegalStateException("none handler.");
    }

    public void setNextHandler(AbstractGenHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
