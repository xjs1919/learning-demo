package com.github.xjs.simple.apollo.spring;

import com.github.xjs.simple.apollo.util.PlaceholderHelper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 9:34
 */
public class SpringValueProcessor implements BeanPostProcessor {

    private SpringValueRegistry springValueRegistry;
    private PlaceholderHelper placeholderHelper;

    public SpringValueProcessor(){
        this.springValueRegistry = SpringValueRegistry.getInstance();
        this.placeholderHelper = PlaceholderHelper.getInstance();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        Class clazz = bean.getClass();
        for (Field field : findAllField(clazz)) {
            processField(bean, beanName, field);
        }
        return bean;
    }

    protected void processField(Object bean, String beanName, Field field) {
        // register @Value on field
        Value value = field.getAnnotation(Value.class);
        if (value == null) {
            return;
        }
        Set<String> keys = placeholderHelper.extractPlaceholderKeys(value.value());
        if (keys.isEmpty()) {
            return;
        }
        for (String key : keys) {
            SpringValue springValue = new SpringValue(key, value.value(), bean, beanName, field);
            springValueRegistry.register(key, springValue);
        }
    }

    private List<Field> findAllField(Class clazz) {
        final List<Field> res = new LinkedList<>();
        ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                res.add(field);
            }
        });
        return res;
    }
}
