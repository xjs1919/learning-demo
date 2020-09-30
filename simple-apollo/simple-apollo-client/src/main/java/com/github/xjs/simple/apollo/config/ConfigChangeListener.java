package com.github.xjs.simple.apollo.config;

import com.github.xjs.simple.apollo.util.PlaceholderHelper;
import com.github.xjs.simple.apollo.spring.SpringValue;
import com.github.xjs.simple.apollo.spring.SpringValueRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Set;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 10:51
 */
public class ConfigChangeListener {

    private static Logger logger = LoggerFactory.getLogger(ConfigChangeListener.class);

    private ConfigurableListableBeanFactory beanFactory;

    private TypeConverter typeConverter;

    private SpringValueRegistry springValueRegistry;

    private PlaceholderHelper placeholderHelper;

    public ConfigChangeListener(ConfigurableListableBeanFactory beanFactory){
        this.beanFactory = beanFactory;
        this.typeConverter = this.beanFactory.getTypeConverter();
        this.springValueRegistry = SpringValueRegistry.getInstance();
        this.placeholderHelper = PlaceholderHelper.getInstance();
    }

    public void onChange(ConfigChangeEvent changeEvent){
        Set<String> keys = changeEvent.changedKeys();
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        for (String key : keys) {
            Collection<SpringValue> targetValues = springValueRegistry.get(key);
            if (targetValues == null || targetValues.isEmpty()) {
                continue;
            }
            for (SpringValue val : targetValues) {
                updateSpringValue(val);
            }
        }
    }

    private void updateSpringValue(SpringValue springValue) {
        try {
            Object value = resolvePropertyValue(springValue);
            springValue.update(value);
        } catch (Throwable ex) {
            logger.error("Auto update apollo changed value failed, {}", springValue.toString(), ex);
        }
    }

    private Object resolvePropertyValue(SpringValue springValue) {
        Object value = placeholderHelper.resolvePropertyValue(beanFactory,
                springValue.getBeanName(), springValue.getPlaceholder());
        return this.typeConverter.convertIfNecessary(value, springValue.getTargetType(), springValue.getField());
    }

}
