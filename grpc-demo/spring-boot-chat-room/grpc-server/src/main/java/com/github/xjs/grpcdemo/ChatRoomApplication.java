package com.github.xjs.grpcdemo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChatRoomApplication implements ApplicationRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ChatRoomApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
