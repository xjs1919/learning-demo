package com.github.xjs.mockbatis.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({MockScannerRegistrar.class})
public @interface MockScan {
    String value() default "";
}
