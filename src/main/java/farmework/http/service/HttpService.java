package farmework.http.service;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class HttpService {

    public String doPostForm(String url, Map<String, Object> params) {
        return doPostForm(url, null, params);
    }

    public String doPostForm(String url, Map<String, String> headers, Map<String, Object> params) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.add(entry.getKey(), String.valueOf(entry.getValue()));
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .post(body)
                .headers(Headers.of(Objects.isNull(headers) ? Maps.newHashMap() : headers))
                .url(url)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            ResponseBody responseBody = response.body();
            return responseBody.string();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String doPostJson(String url, Object data) {
        return doPostJson(url, null, data);
    }

    public String doPostJson(String url, Map<String, String> headers, Object data) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(new Gson().toJson(data), mediaType);
        Request request = new Request.Builder()
                .post(requestBody)
                .headers(Headers.of(Objects.isNull(headers) ? Maps.newHashMap() : headers))
                .url(url)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            ResponseBody responseBody = response.body();
            return responseBody.string();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
