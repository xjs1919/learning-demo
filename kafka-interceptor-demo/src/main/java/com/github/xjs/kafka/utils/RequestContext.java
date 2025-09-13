package com.github.xjs.kafka.utils;

import org.springframework.util.StringUtils;

import java.util.UUID;

public class RequestContext {

    public static final String TRACE_ID = "traceId";
    private static ThreadLocal<String> traceIdHolder = new ThreadLocal<>();

    public static String getTraceId(){
        String traceId = traceIdHolder.get();
        if(!StringUtils.hasLength(traceId)){
            traceId = UUID.randomUUID().toString();
            traceIdHolder.set(traceId);
        }
        return traceId;
    }

    public static void setTraceId(String traceId){
        traceIdHolder.set(traceId);
    }

}
