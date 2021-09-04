package farmework.gen.observer;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import farmework.gen.model.WriteFileContext;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;

public class ReplaceWriteObserver extends AbstractWriteObserver {

    @Override
    protected boolean preWrite(WriteFileContext context) {
        return true;
    }

    @Override
    protected void onWrite(WriteFileContext context) {
        int index = StringUtils.lastIndexOf(context.getOriginFileCode(), "}");
        String newCode =
                StrUtil.replace(context.getOriginFileCode(), index, "}", context.getReplaceCode() + "\n}", false);
        FileUtil.writeString(newCode, context.getJavaFilePath(), Charset.defaultCharset());
    }
}
