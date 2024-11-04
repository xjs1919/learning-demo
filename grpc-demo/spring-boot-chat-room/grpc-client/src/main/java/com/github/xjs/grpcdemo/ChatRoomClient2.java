package com.github.xjs.grpcdemo;

import com.github.xjs.grpcapi.chatroom.ChatMessage;
import com.github.xjs.grpcapi.chatroom.ChatServiceGrpc;
import com.github.xjs.grpcapi.chatroom.Empty;
import com.github.xjs.grpcapi.chatroom.JoinRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChatRoomClient2 {
    public static void main(String[] args) throws Exception {
        String username = "user2";

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        ChatServiceGrpc.ChatServiceStub stub = ChatServiceGrpc.newStub(channel);
        StreamObserver<JoinRequest> requestObserver = stub.joinRoom(new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage chatMessage) {
                System.out.println(chatMessage.getMessage());
            }
            @Override
            public void onError(Throwable throwable) {
                System.out.println("joinRoom onError");
                throwable.printStackTrace();
            }
            @Override
            public void onCompleted() {
                System.out.println("joinRoom onCompleted");
            }
        });
        JoinRequest joinRequest = JoinRequest.newBuilder().setUsername(username).build();
        requestObserver.onNext(joinRequest);
        requestObserver.onCompleted();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String line = br.readLine();
            if(line != null && !line.equals("exit")){
                ChatMessage message = ChatMessage.newBuilder().setMessage(line).setUsername(username).build();
                stub.sendMessage(message, new StreamObserver<Empty>() {
                    @Override
                    public void onNext(Empty empty) {
                        System.out.println("");
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("消息发送异常");
                        throwable.printStackTrace();
                    }
                    @Override
                    public void onCompleted() {
                        System.out.println("消息发送成功");
                    }
                });
            }else{
                break;
            }
        }
        br.close();
        channel.shutdown();
    }
}
