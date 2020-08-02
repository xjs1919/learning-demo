package com.github.xjs.openfeign.demo3interceptor;

import feign.Feign;

public class Client {
    public static void main(String[] args) {
        Itcpt itcpt = Feign.builder()
                .requestInterceptor(new TokenInterceptor())
                .target(Itcpt.class, "http://localhost:8080/");
        System.out.println(itcpt.getByUsername("Joshua"));
    }
}
