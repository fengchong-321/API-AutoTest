package farmework.asserts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import farmework.util.RequiredUtils;

import java.util.Objects;
import java.util.function.Function;

public final class JsonAsserts {

    private static final ObjectMapper MAPPER;

    private String json;

    private JsonAsserts(String json) {
        this.json = json;
    }

    static {
        MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    }

    public static JsonAsserts assertThat(String json) {
        RequiredUtils.requireNotNullOrEmpty(json, "should not be null.");
        return new JsonAsserts(json);
    }

    public static void main(String[] args) {
        String json = "{\n" +
                "\"errNo\": 1000,\n" +
                "\"errMsg\": \"success\",\n" +
                "\"data\": {\n" +
                "\"accountId\": \"3333\",\n" +
                "\"accountName\": \"zhangsan\",\n" +
                "\"userInfo\": {\n" +
                "\"userId\": \"22222\",\n" +
                "\"userName\": \"jim\",\n" +
                "\"dataInfo\": {\n" +
                "\"dataId\": \"11111\"\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}";

        // errNo == 1000,
        // CommonResponseAsserts.assertThat(reponse).requiredSuccess();


//        JsonAsserts.assertThat(json).isEquals("/data/userInfo/dataInfo/dataId", "11112");
//        JsonAsserts.assertThat(json).isEquals("errNo", "1000");
//        JsonAsserts.assertThat(json).isEquals("data.userInfo.dataInfo.dataId", "11112");
//        JsonAsserts.assertThat(json).isLessThan("/errNo", 500);
        JsonAsserts.assertThat(json).isLessThan("/data/userInfo/dataInfo/dataId", "22");
    }

    public JsonAsserts isEquals(String path, String expectValue) {
        return isEquals2(path, expectValue, JsonNode::asText);
    }

    public JsonAsserts isEquals(String path, Integer expectValue) {
        return isEquals2(path, expectValue, JsonNode::asInt);
    }

    public JsonAsserts isEquals(String path, Long expectValue) {
        return isEquals2(path, expectValue, JsonNode::asLong);
    }

    public JsonAsserts isEquals(String path, Boolean expectValue) {
        return isEquals2(path, expectValue, JsonNode::asBoolean);
    }

    public JsonAsserts isEquals(String path, Double expectValue) {
        return isEquals2(path, expectValue, JsonNode::asDouble);
    }

    public JsonAsserts isLessThan(String path, Integer expectValue) {
        return isLessThan(path, expectValue, JsonNode::asInt, exc -> exc);
    }

    public JsonAsserts isLessThan(String path, Long expectValue) {
        return isLessThan(path, expectValue, JsonNode::asLong, exc -> exc);
    }

    public JsonAsserts isLessThan(String path, String expectValue) {
        return isLessThan(path, expectValue, JsonNode::asText, String::length);
    }

    public <T, R extends Number> JsonAsserts isLessThan(String path, T expectValue,
                                                        Function<JsonNode, T> func,
                                                        Function<T, R> valueFunc) {
        T actualValue = requireAt(path, func);
        R expectLessThanValue = valueFunc.apply(expectValue);
        R actualLessThanValue = valueFunc.apply(actualValue);
        if (expectLessThanValue.longValue() <= actualLessThanValue.longValue()) {
            throw new IllegalStateException(String.format("assert less than failed, expect value=%s, but actual value=%s", expectValue, actualValue));
        }
        return this;
    }

    private <T> T requireAt(String path, Function<JsonNode, T> func) {
        try {
            JsonNode rootNode = MAPPER.readTree(this.json);
            JsonNode valueNode = rootNode.requiredAt(path);
            return func.apply(valueNode);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    private <T> JsonAsserts isEquals2(String path, T expectValue, Function<JsonNode, T> func) {
        T actualValue = requireAt(path, func);

        // 处理equals的逻辑
        if (!expectValue.equals(actualValue)) {
            String msg = String.format("assert failed.expect value=%s, but actual value=%s", expectValue, actualValue);
            throw new IllegalStateException(msg);
        }
        return this;
    }

    private <T> JsonAsserts isEquals1(String path, T expectValue, Function<JsonNode, T> func) {
        if (Objects.isNull(expectValue)) {
            String msg = "assert failed, expect value  is null";
            throw new IllegalStateException(msg);
        }

        try {
            JsonNode rootNode = MAPPER.readTree(this.json);
            JsonNode valueNode = rootNode.requiredAt(path);
            T actualValue = func.apply(valueNode);

            if (!expectValue.equals(actualValue)) {
                String msg = String.format("assert failed.expect value=%s, but actual value=%s", expectValue, actualValue);
                throw new IllegalStateException(msg);
            }
            return this;
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}
