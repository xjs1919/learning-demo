package com.github.xjs.iocdemo.inject2;

public class Service3 implements IService{

    private IService service;

    public Service3(IService service) {
        this.service = service;
    }

    public IService getService() {
        return service;
    }
}
