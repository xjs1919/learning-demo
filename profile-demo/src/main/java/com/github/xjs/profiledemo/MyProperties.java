package com.github.xjs.profiledemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author xujs@mamcharge.com
 * @Date 2019/9/3 17:57
 **/
@Configuration
@ConfigurationProperties(prefix = "my.props")
public class MyProperties {
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
