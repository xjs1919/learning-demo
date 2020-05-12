package com.github.xjs.importdemo.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class DemoImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(DemoScan.class.getName()));
        String basePackage = annoAttrs.getString("basePackage");
        System.out.println("basePackage:"+basePackage);
        return new String[]{"com.github.xjs.importdemo.service.Service3"};
    }
}
