package farmework.gen;


import farmework.gen.chain.GenHandlerChain;
import farmework.gen.model.GenContext;
import farmework.gen.observer.ObserverManager;

public final class GenFacade {
    private GenFacade() {

    }

    public static void genBeanSetterCode(GenContext context) {
        ObserverManager.of().gen(context);
    }

    public static String getBeanSetterCode(GenContext context) {
        return GenHandlerChain.of().gen(context);
    }
}
