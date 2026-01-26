package com.github.xjs.springboot4.controller;

import com.github.xjs.springboot4.config.EnvBeanRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Import(BeanRegistrarConfig.class)
@RestController
@RequestMapping("api/bean-registrar")
public class BeanRegistrarController {

    @Autowired
    private EnvBeanRegistrar.EnvService envService;

    @GetMapping(path = "/demo")
    public String demo() throws Exception{
        return envService.getEnv();
    }

}
