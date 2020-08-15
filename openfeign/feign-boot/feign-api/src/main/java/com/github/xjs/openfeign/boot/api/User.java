package com.github.xjs.openfeign.boot.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/8/15 10:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String username;
}
