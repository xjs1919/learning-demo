package com.github.xjs.ajaxserver.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@ControllerAdvice
public class JsonpAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String callback = request.getParameter("callback");
        if(StringUtils.isEmpty(callback)){
            return false;
        }
        return true;
    }
    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = servletRequestAttributes.getRequest();
        HttpServletResponse res =servletRequestAttributes.getResponse();
        try{
            ObjectMapper mapper = new ObjectMapper();
            res.setHeader("Content-Type", "application/javascript;charset=utf-8");
            String callback = req.getParameter("callback");
            String js = callback+"("+ (body==null?"":mapper.writeValueAsString(body))+")";
            OutputStream out = res.getOutputStream();
            out.write(js.getBytes("UTF-8"));
            out.close();
        }catch(Exception e){
            e.printStackTrace();
            return body;
        }
        return null;
    }
}
