package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ThinkPad
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    private static Logger log = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/hello")
    public String getById() {
        log.info("this is a log");
        return "world";
    }

}
