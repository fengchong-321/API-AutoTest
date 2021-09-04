package farmework.holder;

import farmework.cases.model.OldOrder;
import org.junit.jupiter.api.Test;

public class App {

    @Test
    public void test1() {
        OldOrder oldOrder = AutoParamsHolder.getForAppOldOrder().createBean(OldOrder.class);
        System.out.println("oldOrder = " + oldOrder);
    }
}
