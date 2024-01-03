package com.github.xjs.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@ImportResource("classpath:com/github/xjs/bean/lifecycle/beans.xml")
public class UserService implements InitializingBean, DisposableBean, ApplicationContextAware {

    @Autowired
    private InjectedService injectedService;

    public UserService(){
        System.out.println("Constructor, injectedService="+injectedService);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        System.out.println("Aware, injectedService="+injectedService);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean.destroy()");
    }

	@PostConstruct
    public void postConstruct(){
        System.out.println("@PostConstruct");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("@PreDestroy");
    }

    public void initMethod() {
        System.out.println("initMethod");
    }

    public void destroyMethod(){
        System.out.println("destroyMethod");
    }

}