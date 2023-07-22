package com.github.demo.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public String queryById(@PathVariable("id") Long id) {
        return "user=" + id;
    }

}
