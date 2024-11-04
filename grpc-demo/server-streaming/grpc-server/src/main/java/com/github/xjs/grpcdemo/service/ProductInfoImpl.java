package com.github.xjs.grpcdemo.service;

import com.github.xjs.grpcapi.clientstream.Product;
import com.github.xjs.grpcapi.clientstream.ProductGetBatchRequest;
import com.github.xjs.grpcapi.clientstream.ProductInfoGrpc;
import io.grpc.stub.StreamObserver;

public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {

    @Override
    public void getProductBatch(ProductGetBatchRequest request, StreamObserver<Product> responseObserver) {
        int count = request.getCount();
        for(int i=0; i<count; i++){
            Product product = Product.newBuilder().setId(""+(1+1)).setName("product" + i) .setPrice(100+i).build();
            responseObserver.onNext(product);
            System.out.println("发送数据：" + product);
        }
        responseObserver.onCompleted();
        System.out.println("发送完成");
    }
}