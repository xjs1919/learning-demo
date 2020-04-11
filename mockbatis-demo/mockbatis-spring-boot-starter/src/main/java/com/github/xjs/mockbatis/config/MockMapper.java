package com.github.xjs.mockbatis.config;

import com.github.xjs.mockbatis.config.MockScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
@Documented
public @interface MockMapper {
    String value() default "";
}
