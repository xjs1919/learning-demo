package com.github.xjs.iocdemo.inject1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class Service4 {

    @Autowired
    private Service3 service3;

    @Autowired
    @Qualifier("s1")
    private Set<IService> serviceSet;

    public IService getIService(){
        return service3.getIService();
    }

    public Set<IService> getServiceSet(){
        return serviceSet;
    }


}
