package com.github.xjs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 13:35
 */
@RestController
public class ConfigController {

    private Map<String, Map<String, String>> configs = new HashMap<String, Map<String, String>>();

    @PostConstruct
    public void init(){
        Map<String, String> map = new HashMap<>();
        map.put("user.username", "Joshua");
        map.put("user.password", "123456");
        configs.put("app1", map);
    }

    @GetMapping("/get_config")
    public ApolloConfig getConfig(String appid){
        Map<String, String> map = configs.get(appid);
        return new ApolloConfig(appid, map);
    }

    @GetMapping("/update_config")
    public String updateConfig(String appid, String key, String value){
        Map<String, String> map = configs.get(appid);
        if(map != null){
            map.put(key, value);
        }
        return "ok";
    }
}
