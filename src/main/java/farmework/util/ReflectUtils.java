package farmework.util;

import java.lang.reflect.Method;

public final class ReflectUtils {

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    // 如果方法名相同，但是参数列表不一致时这里的获取 可能就有问题
    public static Method getMethod(String className, String methodName) {
        RequiredUtils.requireNotNullOrEmpty(className, "class name should not be null or empty.");
        RequiredUtils.requireNotNullOrEmpty(methodName, "method name should not be null or empty.");
        try {
            Class<?> clazz = Class.forName(className);
            return clazz.getMethod(methodName);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
