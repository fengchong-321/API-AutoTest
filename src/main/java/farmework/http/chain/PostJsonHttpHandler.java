package farmework.http.chain;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import farmework.model.ContentType;
import farmework.model.HttpRequest;
import farmework.model.RequestType;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Objects;

public class PostJsonHttpHandler extends AbstractHttpHandler {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected boolean preRequest(HttpRequest request) {
        return request.getRequestType() == RequestType.POST &&
                request.getContentType() == ContentType.JSON;
    }

    @Override
    protected Request createRequest(HttpRequest request) {
        RequestBody body = RequestBody.create(new Gson().toJson(request.getData()), MEDIA_TYPE);
        return new Request.Builder()
                .post(body)
                .headers(Headers.of(Objects.isNull(request.getHeaderMap()) ? Maps.newHashMap() : request.getHeaderMap()))
                .url(request.getUrl())
                .build();
    }

}
