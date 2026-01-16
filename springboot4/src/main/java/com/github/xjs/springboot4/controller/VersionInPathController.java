package com.github.xjs.springboot4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// configurer.usePathSegment(1);
// or
// spring.mvc.apiversion.use.pathSegment=1
//@RestController
//@RequestMapping("/api/{version}")
public class VersionInPathController {

    /**
     * curl 'http://localhost:8080/api/v1/demo'
     * */
    @GetMapping(version = "v1", path = "/demo")
    public String v1(){
        return "v1";
    }

    /**
     * curl 'http://localhost:8080/api/v2/demo'
     * */
    @GetMapping(version = "v2", path = "/demo")
    public String v2(){
        return "v2";
    }

}
