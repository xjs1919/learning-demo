package com.github.xjs.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Configuration
public class ConfigRefreshAutoConfiguration implements EnvironmentAware, BeanDefinitionRegistryPostProcessor,
        ApplicationListener<RefreshEvent> {

    private Environment environment;
    private BeanDefinitionRegistry registry;

    @Override
    public void setEnvironment(Environment environment){
        this.environment = environment;
    }

    /**
     * 注册RefreshScope这个bean
     * */
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException{
        this.registry = registry;
        //注册RefreshScope，它实现了BeanFactoryPostProcessor，在里面可以注册Scope
        BeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(RefreshScope.class).getBeanDefinition();
        registry.registerBeanDefinition(RefreshScope.class.getName(), bd);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    /**
     * 收到刷新请求
     * */
    @Override
    public void onApplicationEvent(RefreshEvent event) {
        //刷新配置
        RefreshScope.RefreshedConfig refreshedConfig = (RefreshScope.RefreshedConfig) event.getSource();
        String configFileName = refreshedConfig.getYml();
        Map<String, Object> newProperties = refreshedConfig.getProperties();
        PropertySource oldPropertySource = null;
        MutablePropertySources propertySources = ((ConfigurableEnvironment) environment).getPropertySources();
        for (Iterator<PropertySource<?>> it = propertySources.iterator(); it.hasNext(); ) {
            PropertySource propertySource = it.next();
            String propertySourceName = propertySource.getName();
            if (propertySourceName.indexOf(configFileName) > 0) {
                oldPropertySource = propertySource;
                break;
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(newProperties);
        MapPropertySource ps = new MapPropertySource(oldPropertySource.getName(), map);
        ((ConfigurableEnvironment) environment).getPropertySources().remove(oldPropertySource.getName());
        ((ConfigurableEnvironment) environment).getPropertySources().addLast(ps);
        //刷新beans
        String bdNames[] = registry.getBeanDefinitionNames();
        for (String bdName : bdNames) {
            BeanDefinition bd = registry.getBeanDefinition(bdName);
            if (bd.getScope().equals(RefreshScope.class.getSimpleName())) {
                registry.removeBeanDefinition(bdName);
                registry.registerBeanDefinition(bdName, bd);
            }
        }
    }
}
