package com.demo;

import com.github.xjs.config.RefreshScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserProperties properties;

    @Autowired
    RefreshScope refreshScope;

    @GetMapping("/info")
    public String info(){
        return properties.getUsername() + "," + properties.getPassword() +"," + properties.getShare();
    }

    @GetMapping("/refresh")
    public String refresh(String username, String password){
        String propertyFile= "application-dev.yml";
        Map<String, Object> properties = new HashMap<>();
        properties.put("user.username", username);
        properties.put("user.password", password);
        refreshScope.refresh(propertyFile, properties);
        return "success";
    }

}
