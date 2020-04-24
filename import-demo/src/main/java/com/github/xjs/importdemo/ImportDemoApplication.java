package com.github.xjs.importdemo;

import com.github.xjs.importdemo.config.DemoConfig;
import com.github.xjs.importdemo.config.DemoImportSelector;
import com.github.xjs.importdemo.config.DemoRegistrar;
import com.github.xjs.importdemo.config.DemoScan;
import com.github.xjs.importdemo.service.Service1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@DemoScan(basePackage="com.github.xjs")
@Import({Service1.class, DemoConfig.class, DemoImportSelector.class, DemoRegistrar.class})
public class ImportDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImportDemoApplication.class, args);
    }
}
