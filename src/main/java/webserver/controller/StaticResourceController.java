package webserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class StaticResourceController {
    private static final Logger log = LoggerFactory.getLogger(StaticResourceController.class);

    private static final StaticResourceController staticResourceController = new StaticResourceController();
    private StaticResourceController() {
    }

    public static StaticResourceController getInstance(){
        return staticResourceController;
    }

    public HttpResponse service(HttpRequest request){
        HttpResponse response = HttpResponse.responseHeader(request);
        try {
            response.addBody(Files.readAllBytes(new File("./webapp" + request.getUrl()).toPath()));
        }catch (IOException e){
            log.error(e.getMessage());
        }
        return response;
    }


}
