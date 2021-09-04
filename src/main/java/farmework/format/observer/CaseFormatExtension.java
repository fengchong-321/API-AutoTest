package farmework.format.observer;

import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;


/**
 * 第三步,当单侧执行到具体的生命周期节点时，会执行此回调类
 * 用例的格式化检查就在此处理
 */
public class CaseFormatExtension implements BeforeTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        // context.getRequiredTestMethod() ==== course.auto.framework.test.demo1.TestDemo#test222
        FormatManager.INSTANCE.doFormatCheck(context.getRequiredTestMethod());
    }
}
