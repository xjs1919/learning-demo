package com.github.xjs.openfeign.boot.client.controller.hystrix.error;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorConfig {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new MyErrorDecoder();
    }

    public static class MyErrorDecoder implements ErrorDecoder {
        @Override
        public Exception decode(String methodKey, Response response) {
            try{
                //{"timestamp":"2020-08-15T13:38:50.852+00:00","status":500,"error":"Internal Server Error","message":"","path":"/server/hystrix/error/hello"}
                String res = Util.toString(response.body().asReader(Util.UTF_8));
                JsonElement je = new JsonParser().parse(res);
                JsonObject jo = je.getAsJsonObject();
                String path = jo.get("path").getAsString();
                System.out.println("path="+path);
                if(path.equals("/server/hystrix/error/hello")){
                    return new HystrixBadRequestException(res);
                }else{
                    return FeignException.errorStatus(methodKey, response);
                }
            }catch(Exception e){
                e.printStackTrace();
                return e ;
            }
        }
    }
}
