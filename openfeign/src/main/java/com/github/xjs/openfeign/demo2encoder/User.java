package com.github.xjs.openfeign.demo2encoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer age;
    private String username;
    private String password;
}