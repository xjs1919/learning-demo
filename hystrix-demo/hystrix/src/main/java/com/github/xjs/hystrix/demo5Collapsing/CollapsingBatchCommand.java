package com.github.xjs.hystrix.demo5Collapsing;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

public class CollapsingBatchCommand extends HystrixCollapser<List<String>, String, Integer> {
    private final Integer key;
    public CollapsingBatchCommand(Integer key) {
        this.key = key;
    }
    @Override
    public Integer getRequestArgument() {
        return key;
    }
    @Override
    protected HystrixCommand<List<String>> createCommand(final Collection<CollapsedRequest<String, Integer>> requests) {
        return new BatchCommand(requests);
    }
    @Override
    protected void mapResponseToRequests(List<String> batchResponse, Collection<CollapsedRequest<String, Integer>> requests) {
        int count = 0;
        for (CollapsedRequest<String, Integer> request : requests) {
            //响应结果回填回去
            request.setResponse(batchResponse.get(count++));
        }
    }
    private static final class BatchCommand extends HystrixCommand<List<String>> {
        private final Collection<CollapsedRequest<String, Integer>> requests;
        private BatchCommand(Collection<CollapsedRequest<String, Integer>> requests) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey")));
            this.requests = requests;
        }
        @Override
        protected List<String> run() {
            ArrayList<String> response = new ArrayList<String>();
            for (CollapsedRequest<String, Integer> request : requests) {
                //根据不同的请求参数，做不同的响应
                response.add("ValueForKey: " + request.getArgument());
            }
            return response;
        }
    }

    public static void main(String[] args)throws Exception {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            Future<String> f1 = new CollapsingBatchCommand(1).queue();
            Future<String> f2 = new CollapsingBatchCommand(2).queue();
            Future<String> f3 = new CollapsingBatchCommand(3).queue();
            Future<String> f4 = new CollapsingBatchCommand(4).queue();
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
            System.out.println(f4.get());
            //command的执行次数：1次
            System.out.println(HystrixRequestLog.getCurrentRequest().getAllExecutedCommands().size());
            HystrixCommand<?> command = HystrixRequestLog.getCurrentRequest().getAllExecutedCommands().toArray(new HystrixCommand<?>[1])[0];
            //command的名字：GetValueForKey
            System.out.println(command.getCommandKey().name());
            //true
            System.out.println(command.getExecutionEvents().contains(HystrixEventType.COLLAPSED));
            // true
            System.out.println(command.getExecutionEvents().contains(HystrixEventType.SUCCESS));
        } finally {
            context.shutdown();
        }
    }
}