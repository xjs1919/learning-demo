package com.github.xjs.grpcdemo;

import com.github.xjs.grpcapi.clientstream.Product;
import com.github.xjs.grpcapi.clientstream.ProductGetBatchRequest;
import com.github.xjs.grpcapi.clientstream.ProductInfoGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class GrpcClient {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050)
                .usePlaintext()
                .build();

        ProductInfoGrpc.ProductInfoStub stub = ProductInfoGrpc.newStub(channel);
        // 等待接收服务端的响应
        StreamObserver<Product> responseObserver = new StreamObserver<Product>() {
            @Override
            public void onNext(Product result) {
                System.out.println("服务端返回：" + result.toString());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("服务端响应完成");
                // 关闭channel
                channel.shutdown();
                // 结束程序
                countDownLatch.countDown();
            }
        };
        ProductGetBatchRequest request = ProductGetBatchRequest.newBuilder().setCount(10).build();
        stub.getProductBatch(request, responseObserver);
        // 等待结束
        countDownLatch.await();

    }
}
