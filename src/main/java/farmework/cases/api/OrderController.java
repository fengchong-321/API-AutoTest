package farmework.cases.api;

import farmework.cases.model.NewOrder;
import farmework.cases.model.RetMsg;

public class OrderController {

    public RetMsg createOrder(NewOrder order) {
        System.out.println("order = " + order);
        return new RetMsg();
    }
}
