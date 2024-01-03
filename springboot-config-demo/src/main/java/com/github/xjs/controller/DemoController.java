package com.github.xjs.controller;

import com.github.xjs.config.CustomConfigurationProperties;
import com.github.xjs.config.CustomPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {

    /**
     * 方式1: @Value
     * */
    @Value("${app.date}")
    private String date;
    @GetMapping("/atValue")
    public String atValue(){
        return ""+date;
    }

    /**
     * 方式2: @ConfigurationProperties
     * */
    @Autowired
    private CustomConfigurationProperties properties;
    @GetMapping("/configurationProperties")
    public String configurationProperties(){
        return ""+properties.getDate();
    }

    /***
     * 方式3: Environment
     */
    @Autowired
    private Environment environment;
    @GetMapping("/environment")
    public String environment(){
        return ""+environment.getProperty("app.date");
    }

    /***
     * 方式4: @PropertySource
     */
    @Autowired
    private CustomPropertySource propertySource;
    @GetMapping("/propertySource")
    public String propertySource(){
        return ""+propertySource.getAppName();
    }

    /**
     * 5. PropertySourcesPlaceholderConfigurer
     * */
    @Value("${app.author}")
    private String author;
    @GetMapping("/propertySourcesPlaceholderConfigurer")
    public String propertySourcesPlaceholderConfigurer(){
        return ""+author;
    }

}
