package farmework.http.chain;

import com.google.common.collect.Maps;
import farmework.model.ContentType;
import farmework.model.HttpRequest;
import farmework.model.RequestType;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Map;
import java.util.Objects;

public class PostFormHttpHandler extends AbstractHttpHandler {

    @Override
    protected boolean preRequest(HttpRequest request) {
        return request.getRequestType() == RequestType.POST &&
                request.getContentType() == ContentType.FORM;
    }

    @Override
    protected Request createRequest(HttpRequest request) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : request.getParams().entrySet()) {
            builder.add(entry.getKey(), String.valueOf(entry.getValue()));
        }
        RequestBody body = builder.build();

        return new Request.Builder()
                .post(body)
                .headers(Headers.of(Objects.isNull(request.getHeaderMap()) ? Maps.newHashMap() : request.getHeaderMap()))
                .url(request.getUrl())
                .build();
    }

}
