package farmework.gen.chain;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import farmework.gen.model.GenContext;
import farmework.gen.model.GenType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BeanSetterGenHandler extends AbstractGenHandler {

    private static final Map<String, String> DEFAULT_TYPE_RETURN_MAPPING = new HashMap<String, String>() {{
        put("java.lang.Integer", "0");
        put("int", "0");

        put("java.lang.Long", "0L");
        put("long", "0L");

        put("java.lang.Boolean", "false");
        put("boolean", "false");

        put("java.lang.Double", "0.00");
        put("double", "0.00");

        put("java.lang.Float", "0.00f");
        put("float", "0.00f");

        put("java.lang.String", "\"\"");
    }};

    @Override
    protected boolean preGen(GenContext context) {
        return context.getGenType() == GenType.SETTER;
    }

    @Override
    protected String onGen(GenContext context) {
        return context.getForBeanSetterClasses().parallelStream().map(clazz -> {
            Pair<String, String> methodStructPair = createMethodStructCode(clazz);

            List<String> setCodes = Lists.newArrayList();
            createBeanSetterMethodCode(clazz, setCodes);
            String setCode = Joiner.on("\n").join(setCodes);

            return StringUtils.replace(methodStructPair.getLeft(), methodStructPair.getRight(), setCode);
        }).collect(Collectors.joining("\n"));
    }

    private static Pair<String, String> createMethodStructCode(Class<?> clazz) {
        String className = clazz.getSimpleName();
//        String objectName = BeanUtils.getBeanDefineName(className);
        String objectName = null;//TODO BeanUtils.getBeanDefineName(className);
        String searchElement = "${methodBody}";
        String data =
                String.format("\tpublic %s get%s(ParamBuilder builder){\n%s\n\t\treturn %s;\n\t}",
                        className,
                        className,
                        searchElement,
                        objectName);
        return Pair.of(data, searchElement);
    }

    private static void createBeanSetterMethodCode(Class<?> clazz, List<String> setterCodes) {
        String className = clazz.getSimpleName();
//        String objectName = BeanUtils.getBeanDefineName(className);
        String objectName = null;//TODO BeanUtils.getBeanDefineName(className);

        String beanDefine = String.format("\t\t%s %s = new %s();", className, objectName, className);
        setterCodes.add(beanDefine);
        for (Method setMethod : clazz.getMethods()) {
            if (!setMethod.getName().startsWith("set")) {
                continue;
            }

//            Class<?> parameterType = MethodUtils.getSetMethodParameterType(setMethod);
            Class<?> parameterType = null;//TODOMethodUtils.getSetMethodParameterType(setMethod);
//            boolean isJavaType = BeanUtils.isJavaType(parameterType);
            boolean isJavaType = false;//TODO BeanUtils.isJavaType(parameterType);
            if (isJavaType) {
                String setParamCode = "\t\t" + objectName + "." + createBuilderDefault(setMethod);
                setterCodes.add(setParamCode);
            } else {
                createBeanSetterMethodCode(parameterType, setterCodes);
                String setParamCode = "\t\t" + objectName + "." + createBuilderDefault(setMethod);
                setterCodes.add(setParamCode);
            }
        }
    }

    private static String createBuilderDefault(Method setMethod) {
//        Class<?> parameterType = MethodUtils.getSetMethodParameterType(setMethod);
        Class<?> parameterType = null;//TODO MethodUtils.getSetMethodParameterType(setMethod);
        return String.format("%s(builder.getOrDefault(\"%s\", %s));", setMethod.getName(), setMethod.getName(),
                getParameterDefaultValue(parameterType));
    }

    private static String getParameterDefaultValue(Class<?> parameterType) {
//        boolean isJavaType = BeanUtils.isJavaType(parameterType);
        boolean isJavaType = false;//TODO BeanUtils.isJavaType(parameterType);
        if (isJavaType) {
            return DEFAULT_TYPE_RETURN_MAPPING.get(parameterType.getName());
        }

//        return BeanUtils.getBeanDefineName(parameterType.getSimpleName());
        return null;
    }
}
