package com.github.xjs.grpcdemo;

import com.github.xjs.grpcdemo.service.ProductInfoImpl;
import io.grpc.*;

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
                .addStreamTracerFactory(new ServerStreamingInterceptor())
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

    public static class ServerStreamingInterceptor extends ServerStreamTracer.Factory{
        @Override
        public ServerStreamTracer newServerStreamTracer(String s, Metadata metadata) {
            return new ServerStreamTracer(){
                @Override
                public void inboundMessage(int seqNo) {
                    super.inboundMessage(seqNo);
                }

                @Override
                public void inboundWireSize(long bytes) {
                    super.inboundWireSize(bytes);
                }

                @Override
                public void inboundMessageRead(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
                    System.out.println("server: 获得client发送的请求消息 ..." + seqNo+","+optionalWireSize+","+optionalUncompressedSize);
                    super.inboundMessageRead(seqNo, optionalWireSize, optionalUncompressedSize);
                }

                @Override
                public void inboundUncompressedSize(long bytes) {
                    super.inboundUncompressedSize(bytes);
                }

                //outbound 拦截请求
                @Override
                public void outboundMessage(int seqNo) {
                    super.outboundMessage(seqNo);
                }


                @Override
                public void outboundMessageSent(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
                    System.out.println("server: 响应数据的拦截 ..." + seqNo+","+optionalWireSize+","+optionalUncompressedSize);
                    super.outboundMessageSent(seqNo, optionalWireSize, optionalUncompressedSize);
                }

                @Override
                public void outboundWireSize(long bytes) {
                    super.outboundWireSize(bytes);
                }

                @Override
                public void outboundUncompressedSize(long bytes) {
                    super.outboundUncompressedSize(bytes);
                }
            };
        }
    }


}