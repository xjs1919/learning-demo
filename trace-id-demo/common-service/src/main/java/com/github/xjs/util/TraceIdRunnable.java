package com.github.xjs.util;

import com.github.xjs.Constants;
import org.slf4j.MDC;

public class TraceIdRunnable implements Runnable{

    private Runnable target;

    private String traceId;

    public TraceIdRunnable(Runnable target){
        this.target = target;
        this.traceId = MDC.get(Constants.TRACE_ID);
    }

    @Override
    public void run() {
        //真正执行的时候再设置进去
        MDC.put(Constants.TRACE_ID, this.traceId);
        //真正执行的时候再设置进去
        target.run();
    }
}
