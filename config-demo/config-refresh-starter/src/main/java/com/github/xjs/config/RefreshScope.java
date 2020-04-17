package com.github.xjs.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RefreshScope implements Scope, BeanFactoryPostProcessor, ApplicationContextAware {

    private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();

    private ApplicationContext applicationContext;


    public void refresh(String yml, Map<String, Object> properties){
        map.clear();
        applicationContext.publishEvent(new RefreshEvent(new RefreshedConfig(yml,properties)));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope(RefreshScope.class.getSimpleName(), this);
    }

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object obj = map.get(name);
        if(obj == null){
            obj = objectFactory.getObject();
            if(obj != null){
                map.put(name, obj);
            }
        }
        return obj;
    }

    @Override
    public Object remove(String name) {
        return map.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }

    public static class RefreshedConfig{
        private String yml;
        private Map<String, Object>  properties;
        public RefreshedConfig(String yml, Map<String, Object> properties) {
            this.yml = yml;
            this.properties = properties;
        }
        public String getYml() {
            return yml;
        }
        public Map<String, Object> getProperties() {
            return properties;
        }
    }
}

