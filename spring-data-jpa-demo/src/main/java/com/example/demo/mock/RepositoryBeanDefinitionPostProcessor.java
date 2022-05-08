package com.example.demo.mock;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;


/**
 * @author 若鱼1919
 * @date 2022/5/8 0008 11:52
 */
@Component
public class RepositoryBeanDefinitionPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        RepositoryScanner scanner = new RepositoryScanner(beanDefinitionRegistry);
        scanner.addIncludeFilter(new AnnotationTypeFilter(MockRepository.class));
        scanner.scan("com.example.demo.dao");
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
