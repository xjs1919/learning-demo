package com.github.xjs.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 15:35
 */
@Configuration
@ConfigurationProperties(prefix = "user")
public class DemoProperties {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
