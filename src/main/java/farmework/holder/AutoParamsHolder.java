package farmework.holder;

import farmework.cases.model.NewUser;
import farmework.cases.model.OldUser;

public class AutoParamsHolder implements ParamsHolder {
    public String orderId;
    public NewUser payer;
    public NewUser payee;
    public Long amount;
    public OldUser payer1;

    public static AutoParamsHolder newInstance() {
        return new AutoParamsHolder();
    }

    // @Ref("course.auto.framework.cases.TestApiPay2.testCreateOrder")
    public static AutoParamsHolder getForTestCreateOrder() {
        AutoParamsHolder holder = AutoParamsHolder.newInstance();
        holder.orderId = "1234";
        holder.amount = 1024L;
        holder.payer = new NewUser("hshshs");
        holder.payee = new NewUser("hehehe");
        return holder;
    }

    public static AutoParamsHolder getForAppOldOrder() {
        AutoParamsHolder holder = AutoParamsHolder.newInstance();
        holder.payer1 = new OldUser("3sddd");
        return holder;
    }
}
