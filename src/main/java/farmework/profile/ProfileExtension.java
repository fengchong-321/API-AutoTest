package farmework.profile;

import farmework.annotation.EnvProfile;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class ProfileExtension implements BeforeTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        Method testMethod = extensionContext.getRequiredTestMethod();
        EnvProfile profile = testMethod.getAnnotation(EnvProfile.class);
        ProfileHolder.of().setProfile(profile.value());
    }
}
