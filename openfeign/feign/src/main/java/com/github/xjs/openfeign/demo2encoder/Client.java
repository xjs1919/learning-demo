package com.github.xjs.openfeign.demo2encoder;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class Client {
    public static void main(String[] args) {
            Encoder demo = Feign.builder()
                    .encoder(new GsonEncoder())
                    .decoder(new GsonDecoder())
                    .target(Encoder.class, "http://localhost:8080/");
            User u = demo.add(new User(null, "Johusa", "xjs"));
            System.out.println(u);
    }
}
