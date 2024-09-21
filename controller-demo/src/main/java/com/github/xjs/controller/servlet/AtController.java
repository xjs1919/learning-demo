package com.github.xjs.controller.servlet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 方式1：@Controller
 * */
@RestController
public class AtController {
    @GetMapping("/at")
    public String at() {
        return "@Controller";
    }
}
