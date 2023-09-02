package com.github.xjs.feign;

import com.github.xjs.Constants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;

public class TraceIdInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header(Constants.TRACE_ID, MDC.get(Constants.TRACE_ID));
    }
}
