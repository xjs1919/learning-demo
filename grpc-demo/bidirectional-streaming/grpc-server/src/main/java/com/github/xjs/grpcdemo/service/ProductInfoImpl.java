package com.github.xjs.grpcdemo.service;

import com.github.xjs.grpcapi.bidirectionalstream.Product;
import com.github.xjs.grpcapi.bidirectionalstream.ProductInfoGrpc;
import com.github.xjs.grpcapi.bidirectionalstream.ProductSaveResult;
import io.grpc.stub.StreamObserver;

public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {
    @Override
    public StreamObserver<Product> saveProductBatch(StreamObserver<ProductSaveResult> responseObserver) {
        return new StreamObserver<Product>() {
            @Override
            public void onNext(Product product) {
                System.out.println("收到客户端请求：" + product);
                ProductSaveResult result = ProductSaveResult.newBuilder().setSuccess(true).build();
                System.out.println("发送响应");
                responseObserver.onNext(result);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("客户端请求完成");
                responseObserver.onCompleted();
                System.out.println("服务端响应完成");
            }
        };
    }
}