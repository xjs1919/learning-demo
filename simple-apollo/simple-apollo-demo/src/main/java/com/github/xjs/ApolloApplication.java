package com.github.xjs;

import com.github.xjs.simple.apollo.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/29 14:08
 */
@EnableApolloConfig
@SpringBootApplication
public class ApolloApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApolloApplication.class, args);
    }
}
