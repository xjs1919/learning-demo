package com.github.xjs.ajaxserver.filter;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse)response;
        HttpServletRequest req = (HttpServletRequest)request;
        //Origin: http://localhost:8081
        //如果浏览器发出跨域请求，会同时添加自己这个域的Origin头，询问服务端是否允许这个域名的调用
        String  origin = req.getHeader("Origin");
        if(!StringUtils.isEmpty(origin)){
            //服务端返回允许这个域的跨域请求
            res.setHeader("Access-Control-Allow-Origin", origin);
        }
        //如果允许cookie跨域，以下方式不可以！不能使用通配符，同时还得设置Access-Control-Allow-Credentials
        //res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "*");
        //res.setHeader("Access-Control-Allow-Headers", "content-type, x-header1");
        String headers= req.getHeader("Access-Control-Request-Headers");
        if(!StringUtils.isEmpty(headers)){
            res.setHeader("Access-Control-Allow-Headers", headers);
        }
        res.setHeader("Access-Control-Max-Age", "3600");
        //允许cookie跨域
        res.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
