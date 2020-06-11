package com.github.xjs.conditiondemo.demo3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Map;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(EnvConfig.class);
        setProfile(ctx, "product");
        ctx.refresh();
        Map<String, String> beanMap=ctx.getBeansOfType(String.class);
        beanMap.forEach((name, bean)-> System.out.println(name+":"+bean));
        //输出：username:生产环境
    }
    private static void setProfile(AnnotationConfigApplicationContext ctx, String profile){
        Properties properties = new Properties();
        properties.put("spring.profiles.active",profile);
        PropertiesPropertySource propertySource = new PropertiesPropertySource("my", properties);
        ctx.getEnvironment().getPropertySources().addFirst(propertySource);
    }
}
