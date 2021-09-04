package farmework.gen.observer;

import cn.hutool.core.io.FileUtil;
import farmework.gen.model.WriteFileContext;

import java.io.File;
import java.nio.charset.Charset;

public class LoadFileWriteObserver extends AbstractWriteObserver {

    @Override
    protected boolean preWrite(WriteFileContext context) {
        return true;
    }

    @Override
    protected void onWrite(WriteFileContext context) {
        final String code = FileUtil.readString(new File(context.getJavaFilePath()), Charset.defaultCharset());
        context.addOriginFileCode(code);
    }
}
