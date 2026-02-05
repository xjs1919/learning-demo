package com.github.xjs.controller;

import com.github.xjs.service.TokenExtractorService;
import com.github.xjs.service.Wrapper;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequestMapping("/api/nullsafety")
@RestController
public class NullSafetyController {

    @Autowired
    private TokenExtractorService tokenExtractorService;

    // 错误
    //@NonNull TokenExtractorService.Nested nested;
    //正确
    //TokenExtractorService.@NonNull Nested nested;

    @GetMapping("/demo")
    public String demo(){
        String token = tokenExtractorService.extractToken(null);
        if(token == null){
            return "";
        } else {
            return token.toUpperCase();
        }
    }

    @GetMapping("/demo2")
    public String demo2(){
        String token = tokenExtractorService.extractToken("demo2");
        Objects.requireNonNull(token, "token is null");
        return token.toUpperCase();
    }

    @GetMapping("/demo3")
    public String demo3(){
        String token = tokenExtractorService.extractToken("demo3");
        Assert.state(token!=null, "tone is null");
        return token.toUpperCase();
    }

    @GetMapping("/array")
    public String array(){
        String[] token = tokenExtractorService.extractTokens("hello,world");
        return token.toString();
    }

    @GetMapping("/generic")
    public @Nullable String generic(){
        Wrapper<@Nullable String> token = tokenExtractorService.extractTokenWrapped("hello,world");
        return token.unwrap();
    }
}
