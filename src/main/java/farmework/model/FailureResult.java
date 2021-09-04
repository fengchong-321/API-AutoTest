package farmework.model;

import lombok.Data;

@Data
public class FailureResult {
    private String className;
    private String methodName;
    private String parameterTypes;
    private Throwable throwable;
    private String token;

    public static FailureResult of(String className, String methodName, String parameterTypes, String token, Throwable throwable) {
        FailureResult result = new FailureResult();
        result.setClassName(className);
        result.setMethodName(methodName);
        result.setParameterTypes(parameterTypes);
        result.setThrowable(throwable);
        result.setToken(token);
        return result;
    }
}
