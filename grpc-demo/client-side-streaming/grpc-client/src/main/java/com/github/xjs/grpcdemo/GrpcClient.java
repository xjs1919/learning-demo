package com.github.xjs.grpcdemo;

import com.github.xjs.grpcapi.clientstream.Product;
import com.github.xjs.grpcapi.clientstream.ProductAddResult;
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
        StreamObserver<ProductAddResult> responseObserver = new StreamObserver<ProductAddResult>() {
            @Override
            public void onNext(ProductAddResult result) {
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
        StreamObserver<Product> requestObserver = stub.addProductBatch(responseObserver);
        for(int i=0;i<10;i++){
            Product p = Product.newBuilder().setId("" + (i+1))
                    .setPrice(100)
                    .setName("21天精通Java")
                    .setDescription("21天精通Java")
                    .build();
            requestObserver.onNext(p);
        }
        requestObserver.onCompleted();
        // 等待结束
        countDownLatch.await();
    }
}
