package farmework.annotation;

import farmework.format.observer.CaseFormatExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(CaseFormatExtension.class)
@Test
public @interface AutoTest {

    int thinkingTime() default 0;

    boolean parallel() default false;

    int parallelism() default 1;
}
