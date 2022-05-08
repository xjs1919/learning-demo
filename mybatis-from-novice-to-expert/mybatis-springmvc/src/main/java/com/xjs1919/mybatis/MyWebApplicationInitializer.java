package com.xjs1919.mybatis;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author jiashuai.xujs
 * @date 2022/3/24 13:40
 */
public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**这个就是用来加载SpringContext的，类似于之前的springContext.xml*/
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { SpringContextConfig.class };
    }

    /**这个就是用来加载WebContext的，类似于之前的spring-mvc.xml*/
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { SpringMvcConfig.class };
    }

    /**这个是设置DispatcherServlet的映射路径*/
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
