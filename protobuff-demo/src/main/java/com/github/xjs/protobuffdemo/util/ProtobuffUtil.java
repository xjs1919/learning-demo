package com.github.xjs.protobuffdemo.util;

import com.google.protobuf.nano.MessageNano;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

/**
 * @Description
 * @Author xujs@mamcharge.com
 * @Date 2019/8/27 13:00
 **/
public class ProtobuffUtil {

    public static <T extends MessageNano> byte[] toBytes(T nano){
        if(nano == null){
            return new byte[0];
        }
        return MessageNano.toByteArray(nano);
    }

    public static <T extends MessageNano> T fromBytes(byte[] bytes, Class<T> nanoClazz){
        if(bytes == null || bytes.length <= 0){
            return null;
        }
        try{
            T t = nanoClazz.newInstance();
            return MessageNano.mergeFrom(t, bytes);
        }catch(Exception e){
            throw new RuntimeException("反序列化失败", e);
        }
    }

    public static <R, T extends MessageNano> R toBean(T nano, Class<R> beanClazz){
        try{
            R bean = beanClazz.newInstance();
            BeanInfo bi = Introspector.getBeanInfo(beanClazz);
            PropertyDescriptor[] properties = bi.getPropertyDescriptors();
            for(PropertyDescriptor pd : properties){
                String fieldName = pd.getName();
                if(fieldName.equalsIgnoreCase("class")){
                    continue;
                }
                pd.getWriteMethod().invoke(bean, getFieldValue(nano, fieldName));
            }
            return bean;
        }catch(Exception e){
            throw new RuntimeException("nano转化成bean异常", e);
        }
    }

    public static <R extends MessageNano, T> R toNano(T bean, Class<R> nanoClass){
        try{
            R nano = nanoClass.newInstance();
            BeanInfo bi = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] properties = bi.getPropertyDescriptors();
            for(PropertyDescriptor pd : properties){
                String fieldName = pd.getName();
                if(fieldName.equalsIgnoreCase("class")){
                    continue;
                }
                Object fieldValue = pd.getReadMethod().invoke(bean, null);
                setFieldValue(nano, fieldName, fieldValue);
            }
            return nano;
        }catch(Exception e){
            throw new RuntimeException("bean转化成nano异常", e);
        }
    }

    private static <T extends MessageNano> Object getFieldValue(T nano, String name)throws Exception {
        Field field = nano.getClass().getField(name);
        if(field == null){
            return null;
        }
        field.setAccessible(true);
        return field.get(nano);
    }

    private static <T extends MessageNano> void setFieldValue(T nano, String name, Object value)throws Exception {
        Field field = nano.getClass().getField(name);
        if(field == null){
            return;
        }
        field.setAccessible(true);
        field.set(nano, value);
    }
}
