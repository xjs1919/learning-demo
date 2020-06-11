package com.github.xjs.conditiondemo.demo3;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class EvnCondition implements Condition{
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getBeanFactory().getBean(Environment.class);
        String activeProfile = environment.getProperty("spring.profiles.active");
        Map<String, Object> map = metadata.getAnnotationAttributes(Env.class.getName());
        String profile = (String)map.get("profile");
        return profile.equalsIgnoreCase(activeProfile);
    }
}
