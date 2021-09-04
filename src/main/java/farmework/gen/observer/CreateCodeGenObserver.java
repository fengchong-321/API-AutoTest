package farmework.gen.observer;


import farmework.gen.chain.GenHandlerChain;
import farmework.gen.filter.CreateCodeFilterChain;
import farmework.gen.model.GenContext;

public class CreateCodeGenObserver implements IGenObserver {

    @Override
    public void update(GenContext context) {
        // 1.
        CreateCodeFilterChain.of().doFilter(context);

        // 2.
        String code = GenHandlerChain.of().gen(context);
        context.addCodeResult(code);
    }
}
