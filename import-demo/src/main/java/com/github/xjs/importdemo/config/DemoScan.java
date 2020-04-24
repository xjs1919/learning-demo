package com.github.xjs.importdemo.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({DemoImportSelector.class})
public @interface DemoScan {
    /**
     * 要扫描的根路径
     * */
    String basePackage() default "";
}
