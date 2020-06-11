package com.github.xjs.conditiondemo.demo4;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class OnMissingBeanCondition implements Condition{
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> map = metadata.getAnnotationAttributes(OnMissingBean.class.getName(), false);
        Class clazz = (Class)map.get("beanType");
        Map<String, Object> beanMap = context.getBeanFactory().getBeansOfType(clazz);
        return beanMap.size() == 0;
    }
}
