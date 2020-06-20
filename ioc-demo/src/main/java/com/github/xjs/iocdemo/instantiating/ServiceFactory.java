package com.github.xjs.iocdemo.instantiating;

public class ServiceFactory {

    private static int i = 0;

    private ServiceFactory() {}

    public static IService createInstance() {
       return new Service1();
    }

    public IService createService() {
        return new Service2();
    }

}
