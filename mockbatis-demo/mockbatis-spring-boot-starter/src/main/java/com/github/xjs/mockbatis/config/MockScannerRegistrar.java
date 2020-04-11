package com.github.xjs.mockbatis.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.*;

public class MockScannerRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware, ResourceLoaderAware {

    private BeanFactory beanFactory;
    private ResourceLoader resourceLoader;

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void setResourceLoader(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        List<String> packages = null;
        try {
            AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(MockScan.class.getName()));
            if(annoAttrs != null){
                //使用的MockScan进行扫描
                List<String> basePackages = new ArrayList<String>();
                for (String pkg : annoAttrs.getStringArray("value")) {
                    if (StringUtils.hasText(pkg)) {
                        basePackages.add(pkg);
                    }
                }
                packages = basePackages;
            }else{
                //默认扫描
                packages = AutoConfigurationPackages.get(this.beanFactory);
            }
            MockScanner scanner = new MockScanner(registry);
            scanner.setResourceLoader(this.resourceLoader);
            scanner.setAnnotationClass(MockMapper.class);
            scanner.registerFilters();
            scanner.doScan(StringUtils.toStringArray(packages));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public static class MockScanner extends ClassPathBeanDefinitionScanner {
        private Class annotationClass;
        public MockScanner(BeanDefinitionRegistry registry) {
            super(registry);
        }
        public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
            this.annotationClass = annotationClass;
        }
        public void registerFilters() {
            if (this.annotationClass != null) {
                this.addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
            }
        }
        public Set<BeanDefinitionHolder> doScan(String... basePackages) {
            Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
            if (beanDefinitions.isEmpty()) {
                this.logger.warn("No mapper was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
            } else {
                Iterator iterator = beanDefinitions.iterator();
                while(iterator.hasNext()) {
                    BeanDefinitionHolder holder = (BeanDefinitionHolder)iterator.next();
                    GenericBeanDefinition definition = (GenericBeanDefinition)holder.getBeanDefinition();
                    definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName());
                    definition.setBeanClass(MockMapperFactoryBean.class);
                }
            }
            return beanDefinitions;
        }
        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
        }
    }

}
