package com.github.xjs.openfeign.demo1;

import feign.*;

import java.util.Map;

public interface Demo {
    @RequestLine("GET /hello")
    String hello();

    @RequestLine("GET /uriTemplate/{id}")
    String id(@Param("id")String id);

    @RequestLine("GET /paramMap")
    String paramMap(@QueryMap Map<String, String> map);

    @RequestLine("GET /queryMapEncoder")
    String queryMapEncoder(@QueryMap User user);

    @RequestLine("GET /expander/{username}")
    String expander(@Param(value = "username", expander = UserExpander.class)User user);

    public class UserExpander implements Param.Expander{
        @Override
        public String expand(Object value) {
            return ((User)value).getUsername();
        }
    }

    @RequestLine("POST /body")
    @Body("username={username}&password={password}")
    String body(@Param("username")String username, @Param("password")String password);

    @RequestLine("POST /bodyJson")
    @Body("%7B\"username\":\"{username}\",\"password\":\"{password}\"%7D")
    @Headers("Content-Type: application/json")
    String bodyJson(@Param("username")String username, @Param("password")String password);



}
