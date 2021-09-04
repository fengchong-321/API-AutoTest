package farmework.gen.builder;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.stream.Collectors;

public final class ParamBuilder {

    private Map<String, Object> paramMapHolder;

    private ParamBuilder() {
        this.paramMapHolder = Maps.newHashMap();
    }

    public static ParamBuilder builder() {
        return new ParamBuilder();
    }

    public <T> ParamBuilder set(String key, T value) {
        paramMapHolder.put(key, value);
        return this;
    }

    public <T> T getOrDefault(String key, T defaultValue) {
        if (paramMapHolder.containsKey(key)) {
            return (T) paramMapHolder.get(key);
        }

        return defaultValue;
    }

    public String toString() {
        return paramMapHolder.entrySet().stream().map(entry -> {
            // .set("",value);
            return String.format(".set(\"%s\",%s)", entry.getKey(), entry.getValue());
        }).collect(Collectors.joining(""));
    }

    public static void main(String[] args) {
        System.out.println(ParamBuilder.builder().set("setId1", "1111").set("setId2", 222).toString());
    }
}
