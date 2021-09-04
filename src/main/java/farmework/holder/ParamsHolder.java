package farmework.holder;

import farmework.util.RequiredUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

public interface ParamsHolder {


    default <T> T createBean(Class<T> clazz) {
        // 反射啊。
        // class, 是不是就可以拿到这个class的字段名。如：NewOrder，==》 private String orderId;
        // 由于auto params holder实现自此接口，则this(AutoParamsHolder)  public String orderId;
        // orderId === orderId。。。。是不是就是 new class,然后将this中不为null的值赋值过去 就完事了。
        RequiredUtils.requiredNotNull(clazz, "should not be null.");

        try {
            Field[] fields = clazz.getDeclaredFields();
            T instance = clazz.newInstance();

            for (Field field : fields) {
                Field holderField = this.getClass().getField(field.getName());
                Object holderFieldValue = holderField.get(this);
                if (!Objects.isNull(holderFieldValue)) {
                    field.setAccessible(true);
                    field.set(instance, holderFieldValue);
                }
            }
            return instance;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * @param clazz
     * @param mapping 做类型名称映射的，比如 由于命名冲突无法公用，此时换名后，需要做一个映射，即，使得 payer1 ==> payer
     * @param <T>
     * @return
     */
    default <T> T createBean2(Class<T> clazz, Map<String, String> mapping) {
        // 反射啊。
        // class, 是不是就可以拿到这个class的字段名。如：NewOrder，==》 private String orderId;
        // 由于auto params holder实现自此接口，则this(AutoParamsHolder)  public String orderId;
        // orderId === orderId。。。。是不是就是 new class,然后将this中不为null的值赋值过去 就完事了。
        RequiredUtils.requiredNotNull(clazz, "should not be null.");

        try {
            Field[] fields = clazz.getDeclaredFields();
            T instance = clazz.newInstance();

            for (Field field : fields) {
                // TODO 从mapping里找到payer1 然后 get时换成 payer
                Field holderField = this.getClass().getField(field.getName());
                Object holderFieldValue = holderField.get(this);
                if (!Objects.isNull(holderFieldValue)) {
                    field.setAccessible(true);
                    field.set(instance, holderFieldValue);
                }
            }
            return instance;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
