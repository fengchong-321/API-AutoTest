package farmework.annotation;

import farmework.alarm.AlarmExtension;
import farmework.alarm.callback.AlarmCallback;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(AlarmExtension.class)
public @interface TripalAlarm {

    String token();

    Class<? extends AlarmCallback> callback();
}
