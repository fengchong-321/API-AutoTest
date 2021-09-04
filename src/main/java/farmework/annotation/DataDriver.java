package farmework.annotation;

import farmework.driver.DataDriverExtension;
import farmework.format.observer.CaseFormatExtension;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据驱动的注解
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(DataDriverExtension.class)
@ExtendWith(CaseFormatExtension.class)
@TestTemplate // 关键注解
public @interface DataDriver {
    String path();
}
