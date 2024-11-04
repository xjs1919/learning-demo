package com.github.xjs.grpcdemo.service;

import com.github.xjs.grpcapi.Product;
import com.github.xjs.grpcapi.ProductInfoGrpc;
import com.github.xjs.grpcapi.ProductResult;
import io.grpc.stub.StreamObserver;

public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {

    @Override
    public StreamObserver<Product> saveProductBatch(StreamObserver<ProductResult> responseObserver) {
        return new StreamObserver<Product>() {
            @Override
            public void onNext(Product product) {
                System.out.println("收到请求："+product);
                ProductResult result = ProductResult.newBuilder().setSuccess(true).build();
                responseObserver.onNext(result);
            }
            @Override
            public void onError(Throwable throwable) {
            }
            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
                System.out.println("请求处理完毕");
            }
        };
    }
}