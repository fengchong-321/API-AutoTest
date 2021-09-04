package farmework.cases;
import farmework.annotation.*;
import farmework.cases.model.NewOrder;
import farmework.holder.AutoParamsHolder;
import farmework.http.HttpFacade;
import farmework.model.HttpRequest;
import farmework.model.HttpResponse;
import org.assertj.core.api.Assertions;

public class TestApiPay2 {

    @AutoTest
    @CaseTitle("创单接口")
    @CheckPoint("订单落地信息准确")
    @CheckPoint("订单状态机==INIT")
    @CaseDesc(desc = "主流程用例，正常走通全链路", owner = "zhangsan")
    @CaseTag(key = "business", val = "pay")
    @CaseTag(key = "channel", val = "apipay")
    // @EnvCheck("pay", "order", "gateway")
    public void testCreateOrder() {
        // 1.准备环境,清理环境
        // 拿到具体的profile，也知道模块，所以就去check在指定profile环境中的具体模块的保活接口.
        // EnvCheck.check("pay", "order", "gateway");

        // 2.准备数据
//        AutoParamsHolder holder = AutoParamsHolder.getForTestCreateOrder();
//        NewOrder newOrder = new NewOrder();
//        newOrder.setOrderId(holder.orderId);
//        newOrder.setAmount(holder.amount);

        // 3.构造请求
        NewOrder newOrder = AutoParamsHolder.getForTestCreateOrder().createBean(NewOrder.class);

        // 4.向接口发请求
        HttpResponse response = HttpFacade.doRequest(HttpRequest.builder()
                .doPostJson()
                .data(newOrder)
                .build());

        // 5.拿到接口response做断言
        // 目标写法实现... JsonPath($.data.userInfo.addressInfo.address),
        // jackson JsonNode
        // Asserts.assertJson(response.getBody()).isEquals("data.userInfo.addressInfo.address", "beijing");

        Assertions.assertThat("");

        // 原始写法，逐层解析json，拿出数据后做判断是否为对象。。。。。。

        /**
         * response:{
         *     errorCode: 200
         *     errMsg: "success"
         *     data:{
         *         userInfo:{
         *             addressInfo:{
         *                 address: shenzhen
         *             }
         *             name: beijing
         *         }
         *     }
         * }
         */

        // 6.断言db相关逻辑

        // 7.清理
    }
}
