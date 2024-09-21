package com.github.xjs.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@ControllerAdvice
public class JsonpControllerAdvice implements ResponseBodyAdvice<Object> {
    // 参数值必须满足该正则
    private static final Pattern CALLBACK_PARAM_PATTERN = Pattern.compile("[0-9A-Za-z_\\.]*");
    // 参数名称默认callback，你也可以通过配置方式设置
    private static String JSONP_QUERY_PARAM_NAME = "callback";

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //  只要转换器是jackson（json数据输出）
        //  当然你也可以自定义实现，比如：方法上有具体的某个注解等
        return AbstractJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //创建MappingJacksonValue对象（包装原始的数据）
        JsonpMappingJacksonValue container = new JsonpMappingJacksonValue(body);
        // 取得请求的callback参数值
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        String value = servletRequest.getParameter(JSONP_QUERY_PARAM_NAME);
        //  如果不存在直接返回，不做任何处理
        if (value != null) {
            // 不满足条件也直接返回
            if (!CALLBACK_PARAM_PATTERN.matcher(value).matches()) {
                return container;
            }
            //  设置响应头为：application/javascript;charset=utf-8
            MediaType contentTypeToUse = new MediaType("application", "javascript", StandardCharsets.UTF_8);
            response.getHeaders().setContentType(contentTypeToUse);
            // 设置jsonp函数名，后面就会根据该值判断是否要进行处理
            container.setJsonpFunction(value);
        }
        return container;
    }
}