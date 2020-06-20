package com.github.xjs.iocdemo.inject1;

public class Service3 {

    private IService iService;

    public Service3(IService iService){
        this.iService = iService;
    }

    public IService getIService(){
        return iService;
    }

}
