package com.example.demo.mock;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.persistence.EntityManagerFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;

/**
 * @author 若鱼1919
 * @date 2022/5/8 0008 11:08
 */
public class RepositoryFactoryBean implements FactoryBean, ApplicationContextAware {

    private Class repositoryClass;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public RepositoryFactoryBean(Class repositoryClass){
        this.repositoryClass = repositoryClass;
    }

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(repositoryClass.getClassLoader(),
                new Class[]{repositoryClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("===========RepositoryFactoryBean===========");
                        ParameterizedType crudRepositoryClass = (ParameterizedType)repositoryClass.getGenericInterfaces()[0];
                        Class entityClass = Class.forName(crudRepositoryClass.getActualTypeArguments()[0].getTypeName());
                        EntityManagerFactory entityManagerFactory = applicationContext.getBean(EntityManagerFactory.class);
                        RepositoryProxy repositoryProxy = new RepositoryProxy(entityManagerFactory, entityClass);
                        Class repositoryProxyClass = repositoryProxy.getClass();
                        Method repositoryProxyMethod = repositoryProxyClass.getMethod(method.getName(), method.getParameterTypes());
                        return repositoryProxyMethod.invoke(repositoryProxy, args);
                    }
                });
    }

    @Override
    public Class<?> getObjectType() {
        return repositoryClass;
    }

}
