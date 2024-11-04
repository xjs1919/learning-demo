package com.github.xjs.grpcdemo.service;

import com.github.xjs.grpcapi.clientstream.Product;
import com.github.xjs.grpcapi.clientstream.ProductAddResult;
import com.github.xjs.grpcapi.clientstream.ProductInfoGrpc;
import com.google.protobuf.TextFormat;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {

    public io.grpc.stub.StreamObserver<Product> addProductBatch(
            io.grpc.stub.StreamObserver<ProductAddResult> responseObserver) {
        return new StreamObserver<Product>() {
            List<Product> products = new ArrayList<>();
            @Override
            public void onNext(Product product) {
                // 接收客户端请求
                System.out.println(TextFormat.printer().escapingNonAscii(false).printToString(product));
                products.add(product);
            }

            @Override
            public void onError(Throwable throwable) {
                // 错误处理
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                // 客户端请求结束，发送响应
                ProductAddResult result = ProductAddResult.newBuilder().setCount(products.size()).build();
                responseObserver.onNext(result);
                responseObserver.onCompleted();
                System.out.println("服务端响应结束");
            }
        };
    }

}