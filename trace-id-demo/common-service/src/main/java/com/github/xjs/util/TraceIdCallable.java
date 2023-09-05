package com.github.xjs.util;

import com.github.xjs.Constants;
import org.slf4j.MDC;

import java.util.concurrent.Callable;

public class TraceIdCallable<V> implements Callable<V> {
        private Callable<V> target;
        private String traceId;

        public TraceIdCallable(Callable<V> target){
            this.target = target;
            //取ThreadLocal的值并保存起来
            this.traceId = MDC.get(Constants.TRACE_ID);
        }
        @Override
        public V call() throws Exception {
            //真正执行的时候再设置进去
            MDC.put(Constants.TRACE_ID, this.traceId);
            //真正执行的时候再设置进去
            return target.call();
        }
    }