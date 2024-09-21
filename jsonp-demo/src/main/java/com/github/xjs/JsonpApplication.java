package com.github.xjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 参考：https://mp.weixin.qq.com/s/KUnQTSBdXM9jkExsuRFv_A
 * SpringBoot默认响应JSON数据是通过MappingJackson2HttpMessageConverter类，
 * 在该类中的writeInternal方法中会判断当前输出的值是否是MappingJacksonValue，
 * 如果是最终也会获取其中的Value进行输出客户端的。
 *
 * 自定义一个类继承了MappingJacksonValue，同时增加了jsonpFunction的属性，
 * 后面会根据该属性是否有值对结果进行处理，如果没有值则原始返回。
 * 而MappingJacksonValue类的作用就是一个POJO序列化到JSON时提供额外的序列号指令。
 * 在ResponseBodyAdvice中去返回自定义的MappingJacksonValue
 *
 * */
@SpringBootApplication
public class JsonpApplication {
    public static void main(String[] args) {
        SpringApplication.run(JsonpApplication.class, args);
    }
}