package com.github.xjs.simple.apollo.config;

import com.github.xjs.simple.apollo.util.PropertyChangeType;
import com.github.xjs.simple.apollo.repository.RemoteConfigRepository;
import com.github.xjs.simple.apollo.repository.RepositoryChangeListener;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 10:42
 */
public class DefaultConfig implements RepositoryChangeListener {

    private Properties m_resourceProperties;

    private List<ConfigChangeListener> listeners = new ArrayList<>();

    private DefaultConfig(Properties properties){
        this.m_resourceProperties = properties;
    }

    public static DefaultConfig getConfig() {
        RemoteConfigRepository configRepository = new RemoteConfigRepository();
        DefaultConfig defaultConfig = new DefaultConfig(configRepository.getConfig());
        configRepository.addChangeListener(defaultConfig);
        return defaultConfig;
    }

    public void addChangeListener(ConfigChangeListener listener) {
        if(listener!=null){
            listeners.add(listener);
        }
    }

    @Override
    public void onRepositoryChange(Properties newProperties){
        if (newProperties.equals(m_resourceProperties)) {
            return;
        }
        Map<String, ConfigChange> actualChanges = updateAndCalcConfigChanges(newProperties);
        if (actualChanges.isEmpty()) {
            return;
        }
        this.fireConfigChange(new ConfigChangeEvent(actualChanges));
    }

    private void fireConfigChange(ConfigChangeEvent configChangeEvent) {
        for(ConfigChangeListener listener : listeners){
            listener.onChange(configChangeEvent);
        }
    }

    public String getProperty(String name, String defaultValue) {
        String value = m_resourceProperties.getProperty(name);
        return value==null?defaultValue:value;
    }

    public Set<String> getPropertyNames() {
        Map<String, String> h = new LinkedHashMap<>();
        for (Map.Entry<Object, Object> e : m_resourceProperties.entrySet()) {
            Object k = e.getKey();
            Object v = e.getValue();
            if (k instanceof String && v instanceof String) {
                h.put((String) k, (String) v);
            }
        }
        return h.keySet();
    }

    private Map<String, ConfigChange> updateAndCalcConfigChanges(Properties newConfigProperties) {
        List<ConfigChange> configChanges = calcPropertyChanges(m_resourceProperties, newConfigProperties);
        ImmutableMap.Builder<String, ConfigChange> actualChanges = new ImmutableMap.Builder<>();

        for (ConfigChange change : configChanges) {
            actualChanges.put(change.getPropertyName(), change);
        }
        //2. update m_configProperties
        m_resourceProperties = newConfigProperties;
        return actualChanges.build();
    }

    private List<ConfigChange> calcPropertyChanges(Properties previous,
                                           Properties current) {
        if (previous == null) {
            previous = new Properties();
        }

        if (current == null) {
            current = new Properties();
        }

        Set<String> previousKeys = previous.stringPropertyNames();
        Set<String> currentKeys = current.stringPropertyNames();

        Set<String> commonKeys = Sets.intersection(previousKeys, currentKeys);
        Set<String> newKeys = Sets.difference(currentKeys, commonKeys);
        Set<String> removedKeys = Sets.difference(previousKeys, commonKeys);

        List<ConfigChange> changes = Lists.newArrayList();

        for (String newKey : newKeys) {
            changes.add(new ConfigChange(newKey, null, current.getProperty(newKey), PropertyChangeType.ADDED));
        }

        for (String removedKey : removedKeys) {
            changes.add(new ConfigChange(removedKey, previous.getProperty(removedKey), null, PropertyChangeType.DELETED));
        }

        for (String commonKey : commonKeys) {
            String previousValue = previous.getProperty(commonKey);
            String currentValue = current.getProperty(commonKey);
            if (com.google.common.base.Objects.equal(previousValue, currentValue)) {
                continue;
            }
            changes.add(new ConfigChange(commonKey, previousValue, currentValue,
                    PropertyChangeType.MODIFIED));
        }
        return changes;
    }

}
