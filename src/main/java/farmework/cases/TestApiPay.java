package farmework.cases;

import farmework.annotation.*;
import farmework.asserts.JsonAsserts;
import farmework.cases.model.NewOrder;
import farmework.cases.model.NewUser;
import farmework.dac.builder.StatementBuilder;
import farmework.dac.table.AccountTb;
import farmework.model.RowEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class TestApiPay {

    private String orderId;
    private NewUser payer;
    private NewUser payee;
    private Long amount;

    @BeforeEach
    public void init() {
        orderId = "314280";
        amount = 1024L;
    }

    @AutoTest
    @CaseTitle("创单接口")
    @CheckPoint("订单落地信息准确")
    @CheckPoint("订单状态机==INIT")
    @CaseDesc(desc = "主流程用例，正常走通全链路", owner = "fc")
    @CaseTag(key = "business", val = "pay")
    @CaseTag(key = "channel", val = "24")
    // @EnvCheck("pay", "order", "gateway")
    public void testCreateOrder() {
        // 1.准备环境,清理环境
        // 拿到具体的profile，也知道模块，所以就去check在指定profile环境中的具体模块的保活接口.
        // EnvCheck.check("pay", "order", "gateway");

        // 2.准备数据
        NewOrder newOrder = new NewOrder();
        newOrder.setOrderId(orderId);

        // 3.构造请求

        // 4.向接口发请求

        // 5.拿到接口response做断言
        /**
         * {
         *  errorCode: "1000",
         *  errorMsg: "success",
         *  data:{
         *      userInfo:{
         *          name: "xxxx"
         *  }
         * }
         */
//        String response = HttpClient().doPost(url,params);
//
//        JSONObject  obj = JSON.fromJsonObject(response);
//        obj.getJSONObject("data").getJSONObject("userInfo").getString("name")
//
//        String errorCode = obj.getString("errorCode");
//        Asserts.assertRquals(errorCode, "10000");

        // 预期写法
        JsonAsserts.assertThat("response").isEquals("data.userInfo.name", "jim");

        // 6.断言db相关逻辑
        List<RowEntity> entities = new AccountTb().query(StatementBuilder.builder().addStatement("", ""));
        RowEntity entity = entities.get(0);
        entity.getFields().get(0).getFiledName();
        entity.getFields().get(0).getFieldValue();

        //  eq, less, greater
//        DBAsserts.assertThat(new AccountTb()).isEquals("fieldName", "xxxxx");
        // Redis, Kafka, RockectMq, xxxx

        Assertions.assertThat("")
                .isEqualTo("")
                .contains("", "");
        Assertions.assertThat(1);


        // 7.清理
    }
}
