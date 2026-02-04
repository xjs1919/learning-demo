package com.github.xjs.service.impl;

import com.github.xjs.service.TokenExtractorService;
import com.github.xjs.service.Wrapper;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TokenExtractorServiceImpl implements TokenExtractorService {
    @Override
    public java.lang.@Nullable String extractToken(String input) {
        return input;
    }

    public @Nullable String[] extractTokens(String input){
        return Arrays.stream(input.split(",")).map(s->{
            if(s.length() <= 0){
                return null;
            }else {
                return s.toUpperCase();
            }
        }).toList().toArray(new String[0]);
    }

    public Wrapper<@Nullable String> extractTokenWrapped(String input){
        return new Wrapper<>(input.contains("token")?"token":null);
    }

}
