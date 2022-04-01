package webserver.controller;

import db.MemoDataBase;
import db.SessionDataBase;
import model.Memo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.http.Cookie;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MemoController {

    private static final Logger log = LoggerFactory.getLogger(MemoController.class);
    private static final MemoController memoController = new MemoController();
    private static final String INDEX_PAGE_URL = "/index.html";

    private MemoController() {
    }

    public static MemoController getInstance(){
        return memoController;
    }

    public HttpResponse create(HttpRequest request){
        HttpResponse response;
        String sessionId = request.getSessionId();
        Cookie cookie = SessionDataBase.findByUUID(sessionId);
        Memo memo = new Memo(cookie.getUserId(),
                request.getParameter("contents"));
        MemoDataBase.addMemo(memo);
        response = new HttpResponse(request.getVersion(), HttpStatus.FOUND);
        response.addHeader("Content-Type", "text/html;charset=utf-8");
        response.addHeader("Location", INDEX_PAGE_URL);
        return response;
    }

    public HttpResponse createForm(HttpRequest request){
        HttpResponse response = HttpResponse.responseHeader(request);
        try {
            response.addBody(Files.readAllBytes(new File("./webapp" + "/memo/form.html").toPath()));
        }catch (IOException e){
            log.error(e.getMessage());
        }
        return response;
    }
}
