package com.github.xjs.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket服务端处理程序
 * configurator属性可以从Spring容器中去获取Endpoint对象实例
 * */
@Slf4j
@Component
@ServerEndpoint(value = "/ws/{clientId}", configurator = CustomSpringConfigurator.class)
public class WebsocketServer {

    /**
     * 保存clientId和session的对应关系
     * */
    private Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("clientId")String clientId){
        log.info("客户端：{}建立连接", clientId);
        sessionMap.put(clientId, session);
    }

    @OnMessage
    public void onMessage(String msg, @PathParam("clientId")String clientId){
        log.info("收到客户端：{}的消息：{}", msg, clientId);
    }

    @OnClose
    public void onClose(@PathParam("clientId")String clientId){
        log.info("客户端：{}断开连接", clientId);
        sessionMap.remove(clientId);
    }

    /**
     * 主动向client推送消息
     * */
    public void sendToClient(String clientId, String message) throws Exception{
        Session session = sessionMap.get(clientId);
        if(session == null){
            log.error("客户端：{}不在线", clientId);
        }else{
            session.getBasicRemote().sendText(message);
        }
    }
}
