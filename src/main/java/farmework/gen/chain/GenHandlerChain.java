package farmework.gen.chain;


import farmework.gen.model.GenContext;

public final class GenHandlerChain {
    private AbstractGenHandler genHandler;

    private GenHandlerChain() {
        this.genHandler = initHandlerChain();
    }

    private AbstractGenHandler initHandlerChain() {
        AbstractGenHandler beanSetterHandler = new BeanSetterGenHandler();
        return beanSetterHandler;
    }

    private static class ClassHolder {
        private static final GenHandlerChain INSTANCE = new GenHandlerChain();
    }

    public static GenHandlerChain of() {
        return ClassHolder.INSTANCE;
    }

    public String gen(GenContext context) {
        return this.genHandler.doGen(context);
    }
}
