package com.github.xjs.mockbatis.config;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class MockMapperFactoryBean<T> implements FactoryBean {

    private Class<?> mapperClass;

    public MockMapperFactoryBean(Class<?> mapperClass){
        this.mapperClass = mapperClass;
    }

    @Override
    public Class<?> getObjectType() {
        return mapperClass;
    }

    @Override
    public Object getObject() throws Exception {
        Object mapper = Proxy.newProxyInstance(MockMapperFactoryBean.class.getClassLoader(), new Class[]{mapperClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if(method.getDeclaringClass() == Object.class){
                            return method.invoke(this, args);
                        }
                        Select select = method.getAnnotation(Select.class);
                        String sql = select.value();
                        Class<?> returnType = method.getReturnType();
                        System.out.println("proxy begin to execute sql:" + sql+",args:"+ Arrays.toString(args)+",return:"+returnType.getName());
                        return null;
                    }
                });
        return mapper;
    }


}
