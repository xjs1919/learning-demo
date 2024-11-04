package com.github.xjs.grpcdemo.service;

import com.github.xjs.grpcapi.Product;
import com.github.xjs.grpcapi.ProductId;
import com.github.xjs.grpcapi.ProductInfoGrpc;
import com.google.protobuf.TextFormat;
import io.grpc.stub.StreamObserver;

public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {

    @Override
    public void addProduct(Product request, StreamObserver<ProductId> responseObserver) {
        // System.out.println("request.toString() = " + request.toString());
        System.out.println(TextFormat.printer().escapingNonAscii(false).printToString(request));
        ProductId productId = ProductId.newBuilder().setValue(request.getId()).build();
        responseObserver.onNext(productId);
        responseObserver.onCompleted();
    }

    @Override
    public void getProduct(ProductId request, StreamObserver<Product> responseObserver) {
        System.out.println(TextFormat.printer().escapingNonAscii(false).printToString(request));
        Product product = Product.newBuilder().setId(request.getValue()).setName("三国演义").build();
        responseObserver.onNext(product);
        responseObserver.onCompleted();
    }
}