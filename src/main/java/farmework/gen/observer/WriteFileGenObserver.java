package farmework.gen.observer;

import farmework.gen.model.GenContext;
import farmework.gen.model.WriteFileContext;

public class WriteFileGenObserver implements IGenObserver {

    @Override
    public void update(GenContext context) {
        ObserverManager.of().write(WriteFileContext.builder()
                .javaFilePath(context.getJavaFilePath())
                .replaceCode(context.getCodeResult())
                .isCleanFirst(context.isCleanFirst())
                .build());
    }
}
