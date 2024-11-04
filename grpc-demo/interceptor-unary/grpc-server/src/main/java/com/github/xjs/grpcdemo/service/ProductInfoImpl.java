package com.github.xjs.grpcdemo.service;

import com.github.xjs.grpcapi.Product;
import com.github.xjs.grpcapi.ProductId;
import com.github.xjs.grpcapi.ProductInfoGrpc;
import com.google.protobuf.TextFormat;
import io.grpc.stub.StreamObserver;

import java.util.Random;

public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {

    @Override
    public void getProduct(ProductId request, StreamObserver<Product> responseObserver) {
        System.out.println(TextFormat.printer().escapingNonAscii(false).printToString(request));
        try{
            Thread.sleep(new Random().nextInt(2000));
        }catch(Exception e){e.printStackTrace();}
        Product product = Product.newBuilder().setId(request.getValue()).setName("三国演义").build();
        responseObserver.onNext(product);
        responseObserver.onCompleted();
    }
}