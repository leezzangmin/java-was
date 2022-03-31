package webserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

public class UrlMapper {
    private static final Logger log = LoggerFactory.getLogger(UrlMapper.class);
    private UrlMapper() {

    }

    private static final UserController userController = UserController.getInstance();
    private static final IndexController indexController = IndexController.getInstance();
    private static final StaticResourceController staticResourceController = StaticResourceController.getInstance();

    public static HttpResponse getResponse(HttpRequest request) {
        String url = request.getUrl();
        return getResponse(url, request);
    }

    private static HttpResponse getResponse(String url, HttpRequest request) {
        if (url.endsWith(".js") || url.endsWith(".css") || url.endsWith(".ico")) {
            return staticResourceController.service(request);
        }

        // Function<HttpRequest, HttpResponse> 함수형 프로그래밍 개선필요
        switch (url) {
            case "/user/create":
                return userController.join(request);
            case "/index.html":
                return indexController.main(request);
            case "/user/form.html":
                return userController.joinForm(request);
            case "/user/login.html":
                return userController.loginForm(request);
            case "/user/login":
                return userController.login(request);
            case "/user/logout":
                return userController.logout(request);
            case "/user/list":
                return userController.list(request);
            default:
                return null;
        }
    }
}
