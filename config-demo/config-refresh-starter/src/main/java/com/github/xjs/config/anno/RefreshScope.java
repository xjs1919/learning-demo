package com.github.xjs.config.anno;

import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

@Scope("RefreshScope")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RefreshScope {
}
