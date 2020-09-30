package com.github.xjs.simple.apollo.repository;

import java.util.Map;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 13:28
 */
public class ApolloConfig {

    private String appId;

    private Map<String, String> configurations;

    public ApolloConfig() {
    }

    public ApolloConfig(String appId,
                        Map<String, String> configurations) {
        this.appId = appId;
        this.configurations = configurations;
    }

    public String getAppId() {
        return appId;
    }


    public Map<String, String> getConfigurations() {
        return configurations;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setConfigurations(Map<String, String> configurations) {
        this.configurations = configurations;
    }
}
