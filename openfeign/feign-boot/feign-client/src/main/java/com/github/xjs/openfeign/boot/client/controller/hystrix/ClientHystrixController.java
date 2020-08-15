package com.github.xjs.openfeign.boot.client.controller.hystrix;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/client/hystrix")
public class ClientHystrixController {

    @Autowired
    private ServerHystrixFeignClient serverHystrixFeignClient;

    @Autowired
    private ServerHystrixPrototypeClient prototypeClient;

    @GetMapping("/hello")
    public String hystrix(){
        return serverHystrixFeignClient.hello();
    }

    @GetMapping("/prototype")
    public String prototype(){
        return prototypeClient.prototype();
    }


    @GetMapping("/command")
    public String hystrixCommand(){
        try{
            HystrixCommand<String> ret = serverHystrixFeignClient.hystrixCommand();
            return ret.queue().get();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
