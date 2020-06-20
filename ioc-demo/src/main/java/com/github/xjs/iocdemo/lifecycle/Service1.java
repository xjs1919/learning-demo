package com.github.xjs.iocdemo.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.SmartLifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Service1 implements InitializingBean, DisposableBean, SmartLifecycle {

    private int i = 0;

    public Service1(){
        System.out.println("Service1(),i="+i);
        i=10;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet,i="+i);
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy");
    }
    @PostConstruct
    public void postConstruct(){
        System.out.println("JSR250-@PostConstruct,i="+i);
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("JSR250-@PreDestroy");
    }

    public void initMethod(){
        System.out.println("initMethod,i="+i);
    }

    public void destroyMethod(){
        System.out.println("destroyMethod");
    }

    /**参考： https://blog.csdn.net/catoop/article/details/71274561*/
    private boolean isRunning = false;

    /**
     * 如果工程中有多个实现接口SmartLifecycle的类，则这些类的start的执行顺序按getPhase方法返回值从小到大执行。<br/>
     * 例如：1比2先执行，-1比0先执行。 stop方法的执行顺序则相反，getPhase返回值较大类的stop方法先被调用，小的后被调用。
     */
    @Override
    public int getPhase() {
        // 默认为0
        return 0;
    }

    /**
     * 根据该方法的返回值决定是否执行start方法。<br/>
     * 返回true时start方法会被自动执行，返回false则不会。
     */
    @Override
    public boolean isAutoStartup() {
        // 默认为false
        return true;
    }

    /**
     * 1. 我们主要在该方法中启动任务或者其他异步服务，比如开启MQ接收消息<br/>
     * 2. 当上下文被刷新（所有对象已被实例化和初始化之后）时，将调用该方法，默认生命周期处理器将检查每个SmartLifecycle对象的isAutoStartup()方法返回的布尔值。
     * 如果为“true”，则该方法会被调用，而不是等待显式调用自己的start()方法。
     */
    @Override
    public void start() {
        System.out.println("SmartLifecycle#start");
        isRunning = true;
    }

    @Override
    public void stop() {
        System.out.println("SmartLifecycle#stop");
        isRunning = false;
    }

    /**
     * SmartLifecycle子类的才有的方法，当isRunning方法返回true时，该方法才会被调用。
     */
    @Override
    public void stop(Runnable callback) {
        System.out.println("SmartLifecycle#stop(Runnable)");
        // 如果你让isRunning返回true，需要执行stop这个方法，那么就不要忘记调用callback.run()。
        // 否则在你程序退出时，Spring的DefaultLifecycleProcessor会认为你这个SmartLifecycle没有stop完成，程序会一直卡着结束不了，等待一定时间（默认超时时间30秒）后才会自动结束。
        // PS：如果你想修改这个默认超时时间，可以按下面思路做，当然下面代码是springmvc配置文件形式的参考，在SpringBoot中自然不是配置xml来完成，这里只是提供一种思路。
        // <bean id="lifecycleProcessor" class="org.springframework.context.support.DefaultLifecycleProcessor">
        //      <!-- timeout value in milliseconds -->
        //      <property name="timeoutPerShutdownPhase" value="10000"/>
        // </bean>
        callback.run();
        isRunning = false;
    }

    /**
     * 1. 只有该方法返回false时，start方法才会被执行。<br/>
     * 2. 只有该方法返回true时，stop(Runnable callback)或stop()方法才会被执行。
     */
    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
