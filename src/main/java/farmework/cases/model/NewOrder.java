package farmework.cases.model;

import lombok.Data;

@Data
public class NewOrder {

    private String orderId;
    private NewUser payer;
    private NewUser payee;
    private Long amount;

}
