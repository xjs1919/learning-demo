package com.github.xjs.iocdemo.inject1;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Service5 {
    /**这是把容器中的名字叫service1的bean注入*/
    @Resource
    private IService service1;

    /**这是把容器中的名字叫service2的bean注入*/
    @Resource
    private IService service2;

    /**这是把容器中的名字叫service3的bean注入，显然service的类型并不是IService，因此注入失败*/
    //@Resource
    //private IService service3;

    /**这是把容器中的名字叫service33的bean注入，因为容器中根本就没有service33这个bean，因此退化成按类型注入，注入成功*/
    @Resource
    private IService service33;

    public IService getIService1(){
        return service1;
    }

    public IService getIService2(){
        return service2;
    }

}
