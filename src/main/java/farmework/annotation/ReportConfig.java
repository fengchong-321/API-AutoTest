package farmework.annotation;

import farmework.model.ReportType;
import farmework.report.callback.ReportCallback;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReportConfig {

    ReportType type() default ReportType.TRIPAL;

    String token();

    Class<? extends ReportCallback> callback();
}
