package com.github.xjs.springboot4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// configurer.useQueryParam("X-API-VERSION");
// or
// spring.mvc.apiversion.use.queryParameter=X-API-VERSION
//SemanticApiVersionParser
public class VersionInQueryController {

    /**
     * curl 'http://localhost:8080/api/demo?X-API-VERSION=v1'
     * */
    @GetMapping(version = "v1", path = "/demo")
    public String v1(){
        return "v1";
    }

    /**
     *  curl 'http://localhost:8080/api/demo?X-API-VERSION=v2'
     * */
    @GetMapping(version = "v2", path = "/demo")
    public String v2(){
        return "v2";
    }

}
