package com.github.xjs.springboot4.config;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ApiVersionResolver;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Enumeration;

//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        //configurer.useRequestHeader("X-API-VERSION");
        //configurer.usePathSegment(1);
        //configurer.useQueryParam("X-API-VERSION");
        configurer.useMediaTypeParameter(MediaType.APPLICATION_JSON, "X-API-VERSION");
//        configurer.useVersionResolver(
//                request -> {
//                    String headerVersion = request.getHeader("X-API-VERSION");
//                    if (headerVersion != null && headerVersion.length() > 0) {
//                        return headerVersion;
//                    }
//                    String parameterVersion = request.getParameter("X-API-VERSION");
//                    if (parameterVersion != null && parameterVersion.length() > 0) {
//                        return parameterVersion;
//                    }
//                    return "v1";
//                });
    }
}
