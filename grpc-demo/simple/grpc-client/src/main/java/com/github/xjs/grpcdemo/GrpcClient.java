package com.github.xjs.grpcdemo;

import com.github.xjs.grpcapi.Product;
import com.github.xjs.grpcapi.ProductId;
import com.github.xjs.grpcapi.ProductInfoGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050)
                .usePlaintext()
                .build();
        ProductInfoGrpc.ProductInfoBlockingStub stub = ProductInfoGrpc.newBlockingStub(channel);
        Product p = Product.newBuilder().setId("1")
                .setPrice(100)
                .setName("21天精通Java")
                .setDescription("21天精通Java")
                .build();
        ProductId productId = stub.addProduct(p);
        System.out.println("productId.getValue() = " + productId.getValue());
        Product product = stub.getProduct(ProductId.newBuilder().setValue("99999").build());
        System.out.println("product.getName() = " + product.getName());
        channel.shutdown();
    }
}
