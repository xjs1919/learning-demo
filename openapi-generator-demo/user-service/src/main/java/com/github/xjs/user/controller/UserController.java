package com.github.xjs.user.controller;

import com.github.xjs.user.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class UserController {

    Map<Integer, UserDto> db = List.of(new UserDto(10, "Tom"), new UserDto(20, "Jerry"))
                                    .stream()
                                    .collect(Collectors.toMap(UserDto::getId, Function.identity()));


    @GetMapping("/{id}")
    public UserDto getById(@PathVariable int id){
        return db.get(id);
    }

}

