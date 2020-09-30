package com.github.xjs.simple.apollo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 10:24
 */
public class PropertySourcesProcessor implements BeanFactoryPostProcessor, EnvironmentAware, PriorityOrdered {

    public static final String APOLLO_PROPERTY_SOURCE_NAME = "ApolloPropertySources";

    private ConfigurableEnvironment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        initializePropertySources(beanFactory);
    }

    private void initializePropertySources(ConfigurableListableBeanFactory beanFactory) {
        if (environment.getPropertySources().contains(APOLLO_PROPERTY_SOURCE_NAME)) {
            return;
        }
        //添加PropertySource
        CompositePropertySource composite = new CompositePropertySource(APOLLO_PROPERTY_SOURCE_NAME);
        DefaultConfig config = DefaultConfig.getConfig();
        ConfigPropertySource configPropertySource = new ConfigPropertySource("defaultConfigPropertySource",config);
        composite.addPropertySource(configPropertySource);
        environment.getPropertySources().addFirst(composite);
        // 给PropertySource添加监听,实际上就是给config添加监听
        ConfigChangeListener configChangeListener = new ConfigChangeListener(beanFactory);
        configPropertySource.addChangeListener(configChangeListener);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
