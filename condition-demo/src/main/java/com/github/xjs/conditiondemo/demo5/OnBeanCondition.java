package com.github.xjs.conditiondemo.demo5;


import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class OnBeanCondition implements ConfigurationCondition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> map = metadata.getAnnotationAttributes(OnBean.class.getName(), false);
        Class clazz = (Class)map.get("beanType");
        Map<String, Object> beanMap = context.getBeanFactory().getBeansOfType(clazz);
        return beanMap.size() > 0;
    }

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.REGISTER_BEAN;
    }
}
