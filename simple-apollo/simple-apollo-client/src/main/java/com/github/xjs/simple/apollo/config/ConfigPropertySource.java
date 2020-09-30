package com.github.xjs.simple.apollo.config;
import org.springframework.core.env.EnumerablePropertySource;

import java.util.Set;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 10:49
 */
public class ConfigPropertySource extends EnumerablePropertySource<DefaultConfig> {

    private static final String[] EMPTY_ARRAY = new String[0];

    public ConfigPropertySource(String name, DefaultConfig source) {
        super(name, source);
    }

    @Override
    public String[] getPropertyNames() {
        Set<String> propertyNames = this.source.getPropertyNames();
        if (propertyNames.isEmpty()) {
            return EMPTY_ARRAY;
        }
        return propertyNames.toArray(new String[propertyNames.size()]);
    }

    @Override
    public Object getProperty(String name) {
        return this.source.getProperty(name, null);
    }

    public void addChangeListener(ConfigChangeListener listener) {
        this.source.addChangeListener(listener);
    }
}
