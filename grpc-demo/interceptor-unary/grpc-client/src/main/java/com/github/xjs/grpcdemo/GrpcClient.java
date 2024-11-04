package com.github.xjs.grpcdemo;

import com.github.xjs.grpcapi.Product;
import com.github.xjs.grpcapi.ProductId;
import com.github.xjs.grpcapi.ProductInfoGrpc;
import io.grpc.*;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050)
                .usePlaintext()
                .intercept(new ClientLoggingInterceptor())
                .build();
        ProductInfoGrpc.ProductInfoBlockingStub stub = ProductInfoGrpc.newBlockingStub(channel);
        ProductId productId = ProductId.newBuilder().setValue("1").build();
        Product product = stub.getProduct(productId);
        System.out.println("product.getName() = " + product.getName());
        channel.shutdown();
    }

    public static class ClientLoggingInterceptor implements ClientInterceptor{
        @Override
        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel next) {
            System.out.println("执行ClientLoggingInterceptor拦截器...");
            ClientCall<ReqT, RespT> clientCall = next.newCall(methodDescriptor, callOptions);
            // 调用下一个拦截器
            return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(clientCall) {
                @Override
                public void start(Listener<RespT> responseListener, Metadata headers) {
                    // 在调用开始前执行
                    System.out.println("客户端调用：" + methodDescriptor.getFullMethodName());
                    super.start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {
                        @Override
                        public void onMessage(RespT message) {
                            // 收到响应后执行
                            System.out.println("服务端返回：" + message);
                            super.onMessage(message);
                        }
                    }, headers);
                }

            };
        }
    }

}
