package com.github.xjs.grpcdemo.service;

import com.github.xjs.grpcapi.chatroom.ChatMessage;
import com.github.xjs.grpcapi.chatroom.ChatServiceGrpc;
import com.github.xjs.grpcapi.chatroom.Empty;
import com.github.xjs.grpcapi.chatroom.JoinRequest;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@GrpcService
public class ChatRoomService extends ChatServiceGrpc.ChatServiceImplBase {

    private Map<String, StreamObserver<ChatMessage>> userMap = new ConcurrentHashMap();

    @Override
    public StreamObserver<JoinRequest> joinRoom(StreamObserver<ChatMessage> responseObserver) {
       return new StreamObserver<JoinRequest>(){
           private String username ;
           @Override
           public void onNext(JoinRequest joinRequest) {
               System.out.println("joinRoom onNext");
               this.username = joinRequest.getUsername();
               userMap.put(this.username, responseObserver);
               // ChatMessage message = ChatMessage.newBuilder().setMessage(this.username+"已经进入聊天室").build();
               //responseObserver.onNext(message);
               sendToAll("管理员",this.username+"已经进入聊天室");
           }
           @Override
           public void onError(Throwable throwable) {
               System.out.println("joinRoom onError");
               userMap.remove(this.username);
               sendToAll("管理员", this.username+"离开聊天室");
               responseObserver.onCompleted();
           }
           @Override
           public void onCompleted() {
               System.out.println("joinRoom onCompleted");
           }
       };
    }

    @Override
    public void sendMessage(ChatMessage request, StreamObserver<Empty> responseObserver) {
        System.out.println("sendMessage");
        String msg = request.getMessage();
        String fromUser = request.getUsername();
        sendToAll(fromUser, msg);
    }

    public void sendToAll(String fromUser, String message){
        if(userMap.size() <= 0){
            log.error("没有在线用户");
            return;
        }
        for (Map.Entry<String, StreamObserver<ChatMessage>> entry : userMap.entrySet()) {
            ChatMessage chatMessage = ChatMessage.newBuilder()
                    .setUsername(fromUser)
                    .setMessage("[" + fromUser+"]said:" + message).build();
            entry.getValue().onNext(chatMessage);
        }
    }

}
