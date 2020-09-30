package com.github.xjs.simple.apollo.config;

import java.util.Map;
import java.util.Set;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 11:02
 */
public class ConfigChangeEvent {

    private Map<String, ConfigChange> m_changes;

    public ConfigChangeEvent(Map<String, ConfigChange> changes) {
        m_changes = changes;
    }

    public Set<String> changedKeys() {
        return m_changes.keySet();
    }

    public ConfigChange getChange(String key) {
        return m_changes.get(key);
    }

    public boolean isChanged(String key) {
        return m_changes.containsKey(key);
    }

}
