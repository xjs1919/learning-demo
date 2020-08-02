package com.github.xjs.openfeign.demo1;

import feign.Feign;
import feign.QueryMapEncoder;

import java.util.HashMap;
import java.util.Map;

public class Client {

    public static class UserQueryMapEncoder implements feign.QueryMapEncoder {
        @Override
        public Map<String, Object> encode(Object object) {
            User user = (User)object;
            Map<String, Object> map = new HashMap<>();
            map.put("username", user.getUsername());
            map.put("password", user.getPassword());
            return map;
        }
    }

    public static void main(String[] args) {

        Demo demo = Feign.builder()
                .queryMapEncoder(new UserQueryMapEncoder())
                .target(Demo.class, "http://localhost:8080/");

        String result = demo.hello();
        // hello
        System.out.println(result);

        result = demo.id("100");
        //uriTemplate:100
        System.out.println(result);

        Map<String, String> map = new HashMap<>();
        map.put("username", "Joshua");
        map.put("password", "");
        map.put("age", null);//或者不传,效果一样
        //Joshua::null
        System.out.println(demo.paramMap(map));

        //queryMapEncoder:Joshua:123456
        System.out.println(demo.queryMapEncoder(new User(20, "Joshua", "123456")));

        //body:Joshua:123456
        System.out.println(demo.body("Joshua", "123456"));

        //bodyJson:Joshua:123456
        System.out.println(demo.bodyJson("Joshua", "123456"));

        User u = new User(20, "Joshua", "123456");
        //Joshua
        System.out.println(demo.expander(u));
    }
}
