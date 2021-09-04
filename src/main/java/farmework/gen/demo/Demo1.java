package farmework.gen.demo;

import com.google.common.collect.Maps;
import farmework.gen.builder.ParamBuilder;
import farmework.gen.model.OrderInfoGen;
import farmework.gen.model.PayInfoGen;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Demo1 {

    @Test
    public void test1() {
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put("k5", "hahahah");
        OrderInfoGen orderInfoGen = getOrder2(paramMap);
        String resp = testOrder(orderInfoGen);
        System.out.println("resp = " + resp);
    }

    @Test
    public void test2() {
        OrderInfoGen orderInfoGen = getOrder3(ParamBuilder.builder()
                .set("id4", "4")
                .set("id5", "5")
                .set("setId4", "6666")
        );
        String resp = testOrder(orderInfoGen);
        System.out.println("resp = " + resp);
    }

    // 自动代码生成。

    // 使我们想要的样子,但是 我有16个参数，需要你自己写16次set,  我们的getOrder4, 能不能不让我自己写set,而是给我自己生成出来
    public static OrderInfoGen getOrder3(ParamBuilder paramBuilder) {
        OrderInfoGen orderInfoGen = new OrderInfoGen();
        orderInfoGen.setId4(paramBuilder.getOrDefault("setId4", "444"));
        orderInfoGen.setId5(paramBuilder.getOrDefault("setId5", "555"));
        orderInfoGen.setId5(paramBuilder.getOrDefault("setId5", "555"));
        orderInfoGen.setId5(paramBuilder.getOrDefault("setId5", "555"));
        orderInfoGen.setId5(paramBuilder.getOrDefault("setId5", "555"));
        orderInfoGen.setId5(paramBuilder.getOrDefault("setId5", "555"));
        orderInfoGen.setId5(paramBuilder.getOrDefault("setId5", "555"));
        orderInfoGen.setId5(paramBuilder.getOrDefault("setId5", "555"));
        orderInfoGen.setId5(paramBuilder.getOrDefault("setId5", "555"));
        return orderInfoGen;
    }

    public static PayInfoGen getPayInfoGen(ParamBuilder paramBuilder) {
        PayInfoGen payInfoGen = new PayInfoGen();
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        return payInfoGen;
    }

    public static PayInfoGen getPayInfo(ParamBuilder paramBuilder) {
        PayInfoGen payInfoGen = new PayInfoGen();
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        payInfoGen.setId1(paramBuilder.getOrDefault("setId5", "555"));
        return payInfoGen;
    }

    // 看着可以
    private OrderInfoGen getOrder2(Map<String, String> paramMap) {
        OrderInfoGen orderInfoGen = new OrderInfoGen();
        orderInfoGen.setId4(paramMap.getOrDefault("k4", "4"));
        orderInfoGen.setId4(paramMap.getOrDefault("k4", "4"));
        orderInfoGen.setId4(paramMap.getOrDefault("k4", "4"));
        orderInfoGen.setId4(paramMap.getOrDefault("k4", "4"));
        orderInfoGen.setId4(paramMap.getOrDefault("k4", "4"));
        orderInfoGen.setId4(paramMap.getOrDefault("k4", "4"));
        orderInfoGen.setId4(paramMap.getOrDefault("k4", "4"));
        return orderInfoGen;
    }

    // 不行
    private OrderInfoGen getOrder11(String id1, String id2, String id3, String id4) {
        OrderInfoGen orderInfoGen = new OrderInfoGen();
        orderInfoGen.setId4(id1);
        orderInfoGen.setId4(id2);
        orderInfoGen.setId4(id3);
        orderInfoGen.setId4(id4);
        // ...
        return orderInfoGen;
    }

    // 不行
    private OrderInfoGen getOrder1() {
        OrderInfoGen orderInfoGen = new OrderInfoGen();
        orderInfoGen.setId4("");
        orderInfoGen.setId4("");
        orderInfoGen.setId4("");
        orderInfoGen.setId4("");
        orderInfoGen.setId4("");
        orderInfoGen.setId4("");
        orderInfoGen.setId4("");
        orderInfoGen.setId4("");
        orderInfoGen.setId4("");
        orderInfoGen.setId4("");
        return orderInfoGen;
    }

    public static String testOrder(OrderInfoGen orderInfoGen) {
        System.out.println("orderInfoGen = " + orderInfoGen);
        return null;
    }
}
