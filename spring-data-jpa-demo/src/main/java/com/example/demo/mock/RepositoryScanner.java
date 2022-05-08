package com.example.demo.mock;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;


/**
 * @author 若鱼1919
 * @date 2022/5/8 0008 11:52
 */
public class RepositoryScanner extends ClassPathBeanDefinitionScanner {
    public RepositoryScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return metadata.isIndependent() && ( metadata.isInterface());
    }
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> bdhs = super.doScan(basePackages);
        for(BeanDefinitionHolder bdh : bdhs){
            GenericBeanDefinition bd = (GenericBeanDefinition)bdh.getBeanDefinition();
            bd.getConstructorArgumentValues().addGenericArgumentValue(bd.getBeanClassName());
            bd.setBeanClass(RepositoryFactoryBean.class);
        }
        return bdhs;
    }
}
