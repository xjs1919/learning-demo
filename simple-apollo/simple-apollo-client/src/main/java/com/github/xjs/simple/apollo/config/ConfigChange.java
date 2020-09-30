package com.github.xjs.simple.apollo.config;

import com.github.xjs.simple.apollo.util.PropertyChangeType;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 11:03
 */
public class ConfigChange {

    private String propertyName;
    private String oldValue;
    private String newValue;
    private PropertyChangeType changeType;

    public ConfigChange(String propertyName, String oldValue, String newValue,
                        PropertyChangeType changeType) {
        this.propertyName = propertyName;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.changeType = changeType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public PropertyChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(PropertyChangeType changeType) {
        this.changeType = changeType;
    }
}
