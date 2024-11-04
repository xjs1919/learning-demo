package com.github.xjs.grpcdemo.controller;

import com.github.xjs.grpcdemo.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private ChatRoomService chatRoomService;

    @RequestMapping("/sendMsg")
    public String sendToAll(String msg){
        chatRoomService.sendToAll("管理员",msg);
        return "success";
    }

}
