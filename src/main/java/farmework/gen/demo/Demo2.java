package farmework.gen.demo;

import com.google.common.collect.Maps;
import farmework.gen.builder.ParamBuilder;
import farmework.gen.model.OrderInfoGen;

import java.util.Map;


public class Demo2 {

    public static void main(String[] args) {
        Map<String, String> params = Maps.newHashMap();
        params.put("table", "user_info");
        params.put("params", "xyz");
        params.put("url", "xywwwz");
        params.put("param_bean", OrderInfoGen.class.getName());
//        TemplateFacade.create(loadResource("test-normal-case.template"), params,ParamBuilder.builder()
//                .set("setId","111"));
        //

        ParamBuilder.builder().set("id", "11").set("id2", "22");
        ParamBuilder.builder().set("id", "11").set("id2", "22");
    }
}
