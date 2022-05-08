package com.xjs1919.mybatis.shard;

import com.alibaba.druid.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author jiashuai.xujs
 * @date 2022/4/1 13:54
 */
@WebFilter(filterName = "TenantFilter", urlPatterns = {"/*"})
public class TenantFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        TenantHolder.removeTenantId();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        TenantHolder.removeTenantId();
        if (!(request instanceof HttpServletRequest)){
            filterChain.doFilter(request, response);
            return;
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String methodType = httpRequest.getMethod();
        if (!"POST".equals(methodType) &&  !"GET".equals(methodType)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 从session中获取tenantId
        String tenantId = getTenantIdFromSession(httpRequest);
        if(!StringUtils.isEmpty(tenantId)){
            TenantHolder.setTenantId(tenantId);
        }else{
            TenantHolder.removeTenantId();
        }
        try{
            filterChain.doFilter(request, response);
        }finally {
            TenantHolder.removeTenantId();
        }
    }

    public void destroy() {
        TenantHolder.removeTenantId();
        DynamicDataSource.removeDataSource();
    }

    /**
     * 模拟一下
     * */
    private String getTenantIdFromSession(HttpServletRequest httpRequest){
        return httpRequest.getHeader("x-tenant-id");
    }
}
