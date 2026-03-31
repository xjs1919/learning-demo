package com.github.xjs.utils;

public class RequestContext {

    public static final String TRACE_ID = "traceId";
    private static ThreadLocal<String> traceIdHolder = new ThreadLocal<>();

    public static void setTraceId(String traceId){
        traceIdHolder.set(traceId);
    }

    public static String getTraceId() {
        return traceIdHolder.get();
    }

    public static void removeTraceId() {
        traceIdHolder.remove();
    }


}
