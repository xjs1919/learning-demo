package com.github.xjs.simple.apollo.spring;

import org.springframework.core.MethodParameter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 9:39
 */
public class SpringValue {

    private Field field;
    private Object bean;
    private String beanName;
    private String key;
    private String placeholder;
    private Class<?> targetType;

    public SpringValue(String key, String placeholder, Object bean, String beanName, Field field) {
        this.bean = bean;
        this.beanName = beanName;
        this.field = field;
        this.key = key;
        this.placeholder = placeholder;
        this.targetType = field.getType();
    }

    public void update(Object newVal) throws IllegalAccessException, InvocationTargetException {
        if (isField()) {
            injectField(newVal);
        }
    }

    private void injectField(Object newVal) throws IllegalAccessException {
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        field.set(bean, newVal);
        field.setAccessible(accessible);
    }

    public String getBeanName() {
        return beanName;
    }

    public Class<?> getTargetType() {
        return targetType;
    }

    public String getPlaceholder() {
        return this.placeholder;
    }

    public boolean isField() {
        return this.field != null;
    }

    public Field getField() {
        return field;
    }

    @Override
    public String toString() {
        return String
                .format("key: %s, beanName: %s, field: %s.%s", key, beanName, bean.getClass().getName(), field.getName());
    }
}
