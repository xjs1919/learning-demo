package com.github.xjs.openfeign.demo4extend;

import feign.Feign;
import feign.gson.GsonDecoder;

public class Client {
    public static void main(String[] args) {
        UserApi userApi = Feign.builder()
                .decoder(new GsonDecoder())
                .target(UserApi.class, "http://localhost:8080/");
        User u = userApi.getById("100");
        System.out.println(u);
    }
}
