package com.github.xjs.grpcdemo;

import com.github.xjs.grpcdemo.service.ProductInfoImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ProductInfoServer {

    Server server;
 
    public static void main(String[] args) throws IOException, InterruptedException {
        ProductInfoServer server = new ProductInfoServer();
        server.start();
        server.blockUntilShutdown();
    }
 
    public void start() throws IOException {
        int port = 50050;
        server = ServerBuilder.forPort(port)
                .addService(new ProductInfoImpl())
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ProductInfoServer.this.stop();
        }));
        System.out.println("server start on port 50050");
    }
 
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
 
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}