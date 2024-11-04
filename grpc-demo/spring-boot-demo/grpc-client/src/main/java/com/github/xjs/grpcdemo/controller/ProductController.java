package com.github.xjs.grpcdemo.controller;

import com.github.xjs.grpcapi.Product;
import com.github.xjs.grpcapi.ProductId;
import com.github.xjs.grpcapi.ProductInfoGrpc;
import com.github.xjs.grpcdemo.ProductDTO;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GrpcClient("product-service")
    private ProductInfoGrpc.ProductInfoBlockingStub stub;

    @GetMapping("/getProductById")
    public ProductDTO getProductById(Long id){
        ProductId productId = ProductId.newBuilder().setValue(""+id).build();
        Product product = stub.getProduct(productId);
        return ProductDTO.of(product);
    }

}
