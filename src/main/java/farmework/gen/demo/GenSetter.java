package farmework.gen.demo;

import farmework.gen.model.OrderInfoGen;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GenSetter {

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

    public static void main(String[] args) {
        createSetterCode(OrderInfoGen.class);
//        createSetterCode(PayInfoGen.class);
//        OrderInfoGen orderInfoGen = getOrderInfoGen(ParamBuilder.builder()
//                .set("setId2", "333333")
//        );
//        System.out.println(orderInfoGen);

    }

    private static void createSetterCode(Class<?> clazz) {
        String clazzName = clazz.getSimpleName();
        String beanDefineName = getBeanDefineName(clazzName);

        String methodStructFormatter = "public static %s get%s(ParamBuilder paramBuilder) {\n${methodBody}\n}";
        String methodStruct = String.format(methodStructFormatter, clazzName, clazzName);

        String beanDefineStr = String.format("\t%s %s = new %s();\n", clazzName, beanDefineName, clazzName);
        String beenSetMethodStr = Arrays.stream(clazz.getMethods())
                .filter(method -> method.getName().startsWith("set"))
                .map(method -> {
                    String setMethodName = method.getName();
                    String defaultVal = null;
                    // 获取默认值的策略先根据参数名去获取，如果有，就用，然后在根据类型
                    if (getFieldName(setMethodName).equals("createTime")) {
                        defaultVal = "System.currentTimeMillis()";
                    } else {
                        Class<?> parameterType = method.getParameterTypes()[0];
                        defaultVal = DEFAULT_TYPE_RETURN_MAPPING.get(parameterType.getName());
                    }
                    return String.format("\t%s.%s(paramBuilder.getOrDefault(\"%s\", %s));", beanDefineName, setMethodName, setMethodName, defaultVal);
                }).collect(Collectors.joining("\n"));
        String returnStr = String.format("\treturn %s;", beanDefineName);

        String methodBody = String.format("%s%s\n%s", beanDefineStr, beenSetMethodStr, returnStr);

        String code = StringUtils.replace(methodStruct, "${methodBody}", methodBody);

        System.out.println(code);
    }

    private static String getFieldName(String setMethodName) {
        String v1 = StringUtils.substringAfterLast(setMethodName, "set");
        return getBeanDefineName(v1);
    }

    private static String getBeanDefineName(String clazzName) {
        return StringUtils.substring(clazzName, 0, 1).toLowerCase() + StringUtils.substring(clazzName, 1, clazzName.length());
    }

    //  public static OrderInfoGen getOrder3(ParamBuilder paramBuilder) {
    //        OrderInfoGen orderInfoGen = new OrderInfoGen();
    //        orderInfoGen.setId4(paramBuilder.getOrDefault("setId4", "444"));
    //        orderInfoGen.setId4(paramBuilder.getOrDefault("setId4", "444"));
    //        orderInfoGen.setId4(paramBuilder.getOrDefault("setId4", "444"));
    //        return orderInfoGen;
    //    }
}
