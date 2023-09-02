package com.github.xjs.filter;


import com.github.xjs.Constants;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TraceIdFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1.获取request
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 2.获取请求头中的requestId
        String traceId = request.getHeader(Constants.TRACE_ID);
        try {
            // 3.存入MDC
            MDC.put(Constants.TRACE_ID, traceId);
            filterChain.doFilter(request, servletResponse);
        }finally {
            // 4.移除
            MDC.clear();
        }
    }
    @Override
    public void destroy() {
    }
}
