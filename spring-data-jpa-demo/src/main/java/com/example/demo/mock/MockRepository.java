package com.example.demo.mock;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author 若鱼1919
 * @date 2022/5/8 0008 13:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MockRepository {
}
