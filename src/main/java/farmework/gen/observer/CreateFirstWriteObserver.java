package farmework.gen.observer;

import cn.hutool.core.io.FileUtil;
import farmework.gen.model.WriteFileContext;

public class CreateFirstWriteObserver extends AbstractWriteObserver {

    @Override
    protected boolean preWrite(WriteFileContext context) {
        return !FileUtil.exist(context.getJavaFilePath());
    }

    @Override
    protected void onWrite(WriteFileContext context) {
        throw new IllegalStateException("have not support create java file.");
    }
}
