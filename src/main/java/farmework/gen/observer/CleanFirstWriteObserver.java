package farmework.gen.observer;

import cn.hutool.core.util.StrUtil;
import farmework.gen.model.WriteFileContext;
import org.apache.commons.lang3.StringUtils;

public class CleanFirstWriteObserver extends AbstractWriteObserver {

    @Override
    protected boolean preWrite(WriteFileContext context) {
        return context.isCleanFirst();
    }

    @Override
    protected void onWrite(WriteFileContext context) {
        int lastIndex = StringUtils.lastIndexOf(context.getOriginFileCode(), "}");
        int firstIndex = StringUtils.indexOf(context.getOriginFileCode(), "{");
        String body = StrUtil.sub(context.getOriginFileCode(), firstIndex + 1, lastIndex);
        context.addOriginFileCode(StrUtil.replace(context.getOriginFileCode(), body, "\n"));
    }
}
