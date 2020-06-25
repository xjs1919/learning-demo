package com.github.xjs.attarget.config;

import org.apache.catalina.startup.Tomcat;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class DemoWebApplicationInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        //root context
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(DemoConfig.class);
        ctx.refresh();
        //dispatcher servlet
        DispatcherServlet servlet = new DispatcherServlet(ctx);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("servlet", servlet);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");
    }
}
