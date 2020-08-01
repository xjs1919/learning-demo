package com.github.xjs.hystrix.user.service;

import com.github.xjs.hystrix.activity.feign.IFeignActivitService;
import org.springframework.stereotype.Service;

// @Service
public class IFeignActivitServiceFallback implements IFeignActivitService {

    @Override
    public String sendCouponAfterRegister(Long userId) {
        return null;
    }

    @Override
    public String sendCouponAfterRegisterTimeout(Long userId) {
        System.out.println("feign, timeout");
        return "feign, timeout";
    }

    @Override
    public String sendCouponAfterRegisterError(Long userId) {
        System.out.println("feign, error");
        return "feign, error";
    }

    @Override
    public String sendCouponAfterRegisterCircuitOpen(Long userId) {
        System.out.println("feign, CircuitOpen");
        return "feign, CircuitOpen";
    }
}
