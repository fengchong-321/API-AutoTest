package farmework.model;

import lombok.Data;

@Data
public class FailureInfo {

    private String className;
    private String methodName;
    private String parameterTypes;
    private Throwable throwable;
}
