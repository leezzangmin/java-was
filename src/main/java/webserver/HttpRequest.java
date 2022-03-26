package webserver;

import util.HttpRequestUtils;
import util.HttpRequestUtils.Pair;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequest {

    private String httpMethod;
    private String httpUrl;
    private String httpVersion;
    private Map<String, String> params;
    private List<Pair> headers;

    public HttpRequest(String requestLine, List<Pair> headers) throws IOException {
        String[] requestLineSplit = requestLine.split(" ");
        this.httpMethod = requestLineSplit[0];
        this.httpUrl = requestLineSplit[1];
        this.httpVersion = requestLineSplit[2];
        this.params = parseParams(httpUrl);
        this.headers = headers;
    }

    private Map<String, String> parseParams(String httpUrl) {
        String[] splitUrl = httpUrl.split("\\?");
        if (hasQueryString(splitUrl)) {
            return HttpRequestUtils.parseQueryString(URLDecoder.decode(splitUrl[1], StandardCharsets.UTF_8));
        }

        return new HashMap<>();
    }

    private boolean hasQueryString(String[] params) {
        return params.length > 1;
    }

    public String getParameter(String key) {
        return params.get(key);
    }

}