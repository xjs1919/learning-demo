package com.github.xjs.openfeign.demo5errordecoder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import feign.Feign;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

public class Client {

    public static class MyErrorDecoder implements ErrorDecoder {
        @Override
        public Exception decode(String methodKey, Response response) {
            int status = response.status();
            if(status == 500){
                try{
                    String res = Util.toString(response.body().asReader(Util.UTF_8));
                    JsonElement je = new JsonParser().parse(res);
                    JsonObject jo = je.getAsJsonObject();
                    String message = jo.get("message").getAsString();
                    return new BizException(message);
                }catch(Exception e){
                    e.printStackTrace();
                    return e ;
                }
            }else{
                return new BizException("服务端异常");
            }
        }
    }

    public static void main(String[] args) {
        Demo demo = Feign.builder()
                .errorDecoder(new MyErrorDecoder())
                .target(Demo.class, "http://localhost:8080/");
        String result = demo.hello();
        System.out.println(result);
    }
}
