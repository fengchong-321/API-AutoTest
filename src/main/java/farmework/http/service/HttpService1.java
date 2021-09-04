package farmework.http.service;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class HttpService1 {

    public String doPostForm(String url, Map<String, Object> params) {
        return doPostForm(url, null, params);
    }

    public String doPostJson(String url, Object data) {
        return doPostJson(url, null, data);
    }

    private String handleResponse(Request request) {
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            ResponseBody responseBody = response.body();
            return responseBody.string();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String doPostForm(String url, Map<String, String> headers, Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.add(entry.getKey(), String.valueOf(entry.getValue()));
        }
        RequestBody body = builder.build();

        Request request = createRequest(url, headers, body);

        return handleResponse(request);
    }

    private Request createRequest(String url, Map<String, String> headers, RequestBody body) {
        return new Request.Builder()
                .post(body)
                .headers(Headers.of(Objects.isNull(headers) ? Maps.newHashMap() : headers))
                .url(url)
                .build();
    }

    public String doPostJson(String url, Map<String, String> headers, Object data) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(new Gson().toJson(data), mediaType);

        Request request = createRequest(url, headers, body);

        return handleResponse(request);
    }
}
