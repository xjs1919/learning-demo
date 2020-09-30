package com.github.xjs.simple.apollo.spring;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 9:42
 */
public class SpringValueRegistry {

    private static SpringValueRegistry INSTANCE = new SpringValueRegistry();

    private SpringValueRegistry(){}

    public static SpringValueRegistry getInstance(){
        return INSTANCE;
    }

    private final Multimap<String, SpringValue> registry = LinkedListMultimap.create();

    public void register(String key, SpringValue springValue) {
        registry.put(key, springValue);
    }

    public Collection<SpringValue> get(String key) {
        return registry.get(key);
    }
}
