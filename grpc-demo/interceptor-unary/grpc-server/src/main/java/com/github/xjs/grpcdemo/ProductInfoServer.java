package com.github.xjs.grpcdemo;

import com.github.xjs.grpcdemo.service.ProductInfoImpl;
import io.grpc.*;

import java.io.IOException;

public class ProductInfoServer {

    Server server;
 
    public static void main(String[] args) throws IOException, InterruptedException {
        ProductInfoServer server = new ProductInfoServer();
        server.start();
        server.blockUntilShutdown();
    }
 
    public void start() throws IOException {
        int port = 50050;
        server = ServerBuilder.forPort(port)
                .addService(new ProductInfoImpl())
                .intercept(new ServerExecuteTimeInterceptor())
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ProductInfoServer.this.stop();
        }));
        System.out.println("server start on port 50050");
    }
 
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
 
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static class ServerExecuteTimeInterceptor implements ServerInterceptor{
        @Override
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
            String methodName = serverCall.getMethodDescriptor().getFullMethodName();
            System.out.println("receive request :" + methodName);
            ServerCall.Listener<ReqT> listener = next.startCall(serverCall, headers);
            return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(listener) {
                long start=0, end=0;
                String method  = methodName;
                @Override
                public void onHalfClose() {
                    System.out.println("client half close");
                    super.onHalfClose();
                }
                @Override
                public void onCancel() {
                    System.out.println("client cancel");
                    super.onCancel();
                }
                @Override
                public void onComplete() {
                    System.out.println("call complete");
                    super.onComplete();
                    end = System.currentTimeMillis();
                    System.out.println("请求："+method+"耗时：" + (end-start));
                }

                @Override
                public void onMessage(ReqT message) {
                    System.out.println("收到客户端消息:" + message);
                    super.onMessage(message);
                    start = System.currentTimeMillis();
                }
            };
        }
    }


}