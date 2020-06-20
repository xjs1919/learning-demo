package com.github.xjs.iocdemo.scopedemo;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xjs
 */
public class ThreadLocalScope implements Scope {

    private static ThreadLocal<Map<String, Object>> tl = new ThreadLocal<Map<String, Object>>(){
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> map = tl.get();
        Object ret = map.get(name);
        if(ret == null){
            ret = objectFactory.getObject();
            if(ret != null){
                map.put(name, ret);
            }
        }
        return ret;
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> map = tl.get();
        return map.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
