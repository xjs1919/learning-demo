package com.github.xjs.spdemo.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月13日 下午3:45:44<br/>
 * 参考：https://blog.csdn.net/qq_27529917/article/details/79050302
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Import({EnumRegistrar.class})
public @interface EnumComponentScan {
    String[] value() default {};
    String[] basePackages() default {};

}
