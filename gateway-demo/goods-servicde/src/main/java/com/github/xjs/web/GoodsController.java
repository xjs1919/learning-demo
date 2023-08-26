package com.github.xjs.web;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {


    @GetMapping("/{id}")
    public String queryById(@PathVariable("id") Long id) {
        return "goods:"+id;
    }

}
