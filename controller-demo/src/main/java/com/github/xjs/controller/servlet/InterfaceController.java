package com.github.xjs.controller.servlet;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * 方式2：@Component + Controller接口
 * */
@Component("/interface")
public class InterfaceController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        response.getOutputStream().write("Controller implements interface".getBytes(StandardCharsets.UTF_8));
        return null;
    }
}
