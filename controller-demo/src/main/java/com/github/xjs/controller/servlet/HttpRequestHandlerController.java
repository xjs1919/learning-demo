package com.github.xjs.controller.servlet;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 方式3：@Component + HttpRequestHandler
 * */
@Component("/httpRequestHandler")
public class HttpRequestHandlerController implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getOutputStream().write("Controller implements HttpRequestHandler".getBytes(StandardCharsets.UTF_8));
    }
}
