package com.github.xjs.springboot4.controller;

import com.other.PaginationRequest;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    record User(Integer id, String name){ }

    @GetMapping(path = "/user")
    public List<User> users(@Valid @ParameterObject PaginationRequest pagination) {
        return List.of();
    }

}
