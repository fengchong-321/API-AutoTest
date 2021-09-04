package farmework.driver;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import farmework.annotation.DataDriver;
import farmework.annotation.DataParam;
import farmework.model.DataEntity;
import farmework.util.YmlUtils;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.stream.Stream;

public class DataDriverExtension implements TestTemplateInvocationContextProvider {

    @Override
    public boolean supportsTestTemplate(ExtensionContext extensionContext) {
        // 判断要被执行的方法之上被标识了注解 @DataDriver
        return extensionContext.getTestMethod()
                .filter(method -> AnnotationSupport.isAnnotated(method, DataDriver.class))
                .isPresent();
    }

    /**
     * 数据驱动，数据处理
     * 1.找到DataDriver 指定的 path 路径下的文件，并解析出来
     * 2.解析出来的数据是List<T>
     * 3.将 T-> new DataDriverInvocationContext()
     * 4.最后将所有List中的T都转成了DataDriverInvocationContext之后，整体装进Stream做返回
     *
     * @param context
     * @return
     */
    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        Method testMethod = context.getRequiredTestMethod();
        DataDriver driver = testMethod.getAnnotation(DataDriver.class);
        List<DataEntity> dataEntities = YmlUtils.read(driver.path());
        return dataEntities.stream().map(DataDriverInvocationContext::new);
    }

    /**
     * testTemplate(测试模板)，包含测试数据。
     */
    static class DataDriverInvocationContext implements TestTemplateInvocationContext, ParameterResolver {

        private DataEntity dataEntity;

        public DataDriverInvocationContext(DataEntity dataEntity) {
            this.dataEntity = dataEntity;
        }

        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            return extensionContext.getTestMethod()
                    .filter(method -> AnnotationSupport.isAnnotated(method, DataDriver.class))
                    .isPresent();
        }

        /**
         * 将yml文件转成对象
         * 将name: zhangsan1111（yml） -> String name = "zhangsan1111";
         * @param parameterContext
         * @param extensionContext
         * @return
         * @throws ParameterResolutionException
         */
        @Override
        public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            Parameter parameter = parameterContext.getParameter();
            DataParam dataParam = parameter.getAnnotation(DataParam.class);
            if (this.dataEntity.isKeyExists(dataParam.value())) {
                DataEntity.Entity entity = this.dataEntity.getEntity(dataParam.value());
                if (entity.isJavaBean()) {
                    return new Gson().fromJson(entity.getVal(), parameter.getType());
                }
                return transForJavaType(entity.getVal(), parameter.getType());
            }
            throw new IllegalStateException("parameter name for " + dataParam.value() + " not exists.");
        }

        private Object transForJavaType(String val, Class<?> type) {
            switch (type.getName()) {
                case "java.lang.Integer":
                    return Integer.valueOf(val);
                case "java.lang.Long":
                    return Long.valueOf(val);
                case "java.lang.Boolean":
                    return Boolean.valueOf(val);
                case "java.lang.String":
                    return val;
                default:
                    throw new IllegalStateException("have not support this type = " + type.getName());
            }
        }

        @Override
        public String getDisplayName(int invocationIndex) {
            return "data driver:" + invocationIndex;
        }

        @Override
        public List<Extension> getAdditionalExtensions() {
            return Lists.newArrayList(this);
        }
    }
}
