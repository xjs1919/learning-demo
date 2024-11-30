package com.github.xjs.controller;

import com.github.xjs.producer.Producer;
import com.github.xjs.protocol.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private Producer producer;

    @GetMapping("/send/{id}/{name}")
    public String send(@PathVariable("id")Integer id, @PathVariable("name")String name){
        UserRecord user = new UserRecord();
        user.setId(id);
        user.setName(name);
        producer.send(user);
        return "send message-->id:" + id + ",name = " + name;
    }
}
