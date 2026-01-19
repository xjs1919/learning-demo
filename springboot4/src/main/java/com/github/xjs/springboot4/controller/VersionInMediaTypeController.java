package com.github.xjs.springboot4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// configurer.useMediaTypeParameter(MediaType.APPLICATION_JSON, "X-API-VERSION");
// or
// spring.mvc.apiversion.use.mediaTypeParameter.application/json=X-API-VERSION
//@RestController
//@RequestMapping("/api")
public class VersionInMediaTypeController {

    /**
     * curl localhost:8080/api/demo -H 'Accept: application/json; X-API-VERSION=v1'
     * */
    @GetMapping(version = "v1", path = "/demo")
    public String v1(){
        return "v1";
    }

    /**
     * curl localhost:8080/api/demo -H 'Accept: application/json; X-API-VERSION=v2'
     * */
    @GetMapping(version = "v2", path = "/demo")
    public String v2(){
        return "v2";
    }

}
