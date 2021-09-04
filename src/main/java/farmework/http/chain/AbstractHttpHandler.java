package farmework.http.chain;

import farmework.model.HttpRequest;
import farmework.model.HttpResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public abstract class AbstractHttpHandler {
    private AbstractHttpHandler nextHandler;

    protected abstract boolean preRequest(HttpRequest request);

    protected abstract Request createRequest(HttpRequest request);

    private HttpResponse onRequest(HttpRequest request) {
        Request req = createRequest(request);
        return handleResponse(req);
    }

    public HttpResponse doRequest(HttpRequest request) {
        Objects.requireNonNull(request, "http request should not be null.");
        if (preRequest(request)) {
            return onRequest(request);
        }

        if (!Objects.isNull(this.nextHandler)) {
            return this.nextHandler.doRequest(request);
        }

        throw new IllegalStateException("unknow http request:" + request);
    }

    private HttpResponse handleResponse(Request request) {
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if (!response.isSuccessful()) {
                throw new IllegalStateException("do http failed.");
            }

            ResponseBody responseBody = response.body();
            if (!Objects.isNull(responseBody)) {
                return new HttpResponse(responseBody.string());
            }

            throw new IllegalStateException("get http response failed.");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void setNextHandler(AbstractHttpHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
