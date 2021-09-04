package farmework.model;

import java.util.Map;

public class HttpRequest {
    private String url;
    private RequestType requestType;
    private ContentType contentType;
    private Map<String, String> headerMap;
    private Map<String, Object> params;
    private Object data;

    private HttpRequest(Builder builder) {
        this.url = builder.url;
        this.requestType = builder.requestType;
        this.contentType = builder.contentType;
        this.headerMap = builder.headerMap;
        this.params = builder.params;
        this.data = builder.data;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String url;
        private RequestType requestType;
        private ContentType contentType;
        private Map<String, String> headerMap;
        private Map<String, Object> params;
        private Object data;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder requestType(RequestType requestType) {
            this.requestType = requestType;
            return this;
        }

        public Builder doPost() {
            this.requestType = RequestType.POST;
            return this;
        }

        public Builder doGet() {
            this.requestType = RequestType.GET;
            return this;
        }

        public Builder contentType(ContentType contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder forJson() {
            this.contentType = ContentType.JSON;
            return this;
        }

        public Builder forForm() {
            this.contentType = ContentType.FORM;
            return this;
        }

        public Builder doPostForm() {
            this.contentType = ContentType.FORM;
            this.requestType = RequestType.POST;
            return this;
        }

        public Builder doPostJson() {
            this.contentType = ContentType.JSON;
            this.requestType = RequestType.POST;
            return this;
        }

        public Builder headerMap(Map<String, String> headerMap) {
            this.headerMap = headerMap;
            return this;
        }

        public Builder params(Map<String, Object> params) {
            this.params = params;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }

    public String getUrl() {
        return url;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "url='" + url + '\'' +
                ", requestType=" + requestType +
                ", contentType=" + contentType +
                ", headerMap=" + headerMap +
                ", params=" + params +
                ", data=" + data +
                '}';
    }
}
