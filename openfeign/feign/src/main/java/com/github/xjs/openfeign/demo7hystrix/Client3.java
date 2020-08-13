package com.github.xjs.openfeign.demo7hystrix;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.*;
import feign.codec.ErrorDecoder;
import feign.hystrix.FallbackFactory;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

public class Client3 {

    public static class MyFallbackFactory implements FallbackFactory<Demo>{
        @Override
        public Demo create(Throwable throwable) {
            System.out.println(throwable.getClass().getName());
            return new Demo() {
                @Override
                public String sync(int id, String username) {
                    return null;
                }
                @Override
                public HystrixCommand<String> async1(int id, String username) {
                    return null;
                }
                @Override
                public CompletableFuture<String> async2(int id, String username) {
                    return null;
                }
                @Override
                public String getById(Integer id) {
                    return "fallback";
                }
                @Override
                public String getByName(String username){
                    return "fallback name";
                }
            };
        }
    }

    public static class MyErrorDecoder implements ErrorDecoder{
        @Override
        public Exception decode(String methodKey, Response response) {
            try{
                String json = Util.toString(response.body().asReader(Util.UTF_8));
                JsonElement je = new JsonParser().parse(json);
                JsonObject jo = je.getAsJsonObject();
                String message = jo.get("message").getAsString();
                String type = message.substring(0,message.indexOf(":"));
                String msg = message.substring(message.indexOf(":")+1);
                if("1".equals(type)){
                    return new HystrixBadRequestException(msg);
                }
                return FeignException.errorStatus(methodKey, response);
            }catch (Exception e){
                e.printStackTrace();
                return e;
            }
        }
    }

    public static void main(String[] args)throws Exception {
        Demo demo = HystrixFeign.builder()
                .errorDecoder(new MyErrorDecoder())
                .target(Demo.class,
                        "http://localhost:8080/",
                        new MyFallbackFactory());
//        String result = demo.getById(100);
//        System.out.println(result);
        String name = demo.getByName("Joshua");
        System.out.println(name);
    }
}
