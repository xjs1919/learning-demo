package com.github.xjs.grpcdemo;

import com.github.xjs.grpcapi.Product;
import com.github.xjs.grpcapi.ProductInfoGrpc;
import com.github.xjs.grpcapi.ProductResult;
import io.grpc.*;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class GrpcClient {
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050)
                .usePlaintext()
                .intercept(new ClientStreamingInterceptor())
                .build();
        ProductInfoGrpc.ProductInfoStub stub = ProductInfoGrpc.newStub(channel);
        StreamObserver<Product> requestObserver = stub.saveProductBatch(new StreamObserver<ProductResult>() {
            @Override
            public void onNext(ProductResult productResult) {
                System.out.println("服务端返回:" + productResult.getSuccess());
            }
            @Override
            public void onError(Throwable throwable) {
            }
            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });
        for (int i = 0; i < 10; i++) {
            Product p = Product.newBuilder().setId(""+i).setName("p"+i).build();
            System.out.println("客户端发送:" + p);
            requestObserver.onNext(p);
        }
        requestObserver.onCompleted();
        System.out.println("客户端发送完成");
        countDownLatch.await();

    }

    public static class ClientStreamingInterceptor implements ClientInterceptor{
        @Override
        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
            System.out.println("执行ClientStreamingInterceptor拦截器...");
            //把自己开发的ClientStreamTracerFactory融入到gRPC体系
            callOptions = callOptions.withStreamTracerFactory(new ClientStreamTracer.Factory() {
                @Override
                public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo info, Metadata headers) {
                    return new ClientStreamTracer() {
                        @Override
                        //用于输出响应头
                        public void outboundHeaders() {
                            System.out.println("client: 用于输出请求头.....");
                            super.outboundHeaders();
                        }

                        @Override
                        //设置消息编号
                        public void outboundMessage(int seqNo) {
                            System.out.println("client: 设置流消息的编号: " + seqNo);
                            super.outboundMessage(seqNo);
                        }

                        @Override
                        public void outboundUncompressedSize(long bytes) {
                            System.out.println("client: 获得未压缩消息的大小:" + bytes);
                            super.outboundUncompressedSize(bytes);
                        }

                        @Override
                        //用于获得 输出消息的大小
                        public void outboundWireSize(long bytes) {
                            System.out.println("client: 用于获得 输出消息的大小:" + bytes);
                            super.outboundWireSize(bytes);
                        }

                        @Override
                        //拦截消息发送
                        public void outboundMessageSent(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
                            System.out.println("client: 监控请求操作 outboundMessageSent:" + seqNo);
                            super.outboundMessageSent(seqNo, optionalWireSize, optionalUncompressedSize);
                        }

                        //inbound  对于相应相关操作的拦截
                        @Override
                        public void inboundHeaders() {
                            System.out.println("用于获得响应头....");
                            super.inboundHeaders();
                        }

                        @Override
                        public void inboundMessage(int seqNo) {
                            System.out.println("获得响应消息的编号..." + seqNo);
                            super.inboundMessage(seqNo);
                        }

                        @Override
                        public void inboundWireSize(long bytes) {
                            System.out.println("获得响应消息的大小... " + bytes);
                            super.inboundWireSize(bytes);
                        }

                        @Override
                        public void inboundMessageRead(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
                            System.out.println("集中获得消息的编号 ，大小 ，未压缩大小..." + seqNo +" " + optionalWireSize +" "+ optionalUncompressedSize);
                            super.inboundMessageRead(seqNo, optionalWireSize, optionalUncompressedSize);
                        }

                        @Override
                        public void inboundUncompressedSize(long bytes) {
                            System.out.println("获得响应消息未压缩大小..." + bytes);
                            super.inboundUncompressedSize(bytes);
                        }

                        @Override
                        public void inboundTrailers(Metadata trailers) {
                            System.out.println("响应结束..");
                            super.inboundTrailers(trailers);
                        }
                    };
                }
            });
            return next.newCall(method, callOptions);
        }
    }


}
