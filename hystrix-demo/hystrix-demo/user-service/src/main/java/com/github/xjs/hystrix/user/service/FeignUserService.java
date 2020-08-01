package com.github.xjs.hystrix.user.service;

import com.github.xjs.hystrix.activity.feign.IFeignActivitService;
import com.github.xjs.hystrix.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignUserService {

    @Autowired
    // private FeignActivitService activityService;
    //private IFeignActivitService activityService;
    private IFeignActivitService activityService;

    public String register(User user) {
        System.out.println("feign,用户注册成功：" + user);
        activityService.sendCouponAfterRegister(user.getId());
        return "success";
    }

    public String registerTimeout(User user) {
        System.out.println("feign,用户注册成功：" + user);
        activityService.sendCouponAfterRegisterTimeout(user.getId());
        return "success";
    }

    public String registerError(User user) {
        System.out.println("feign,用户注册成功：" + user);
        activityService.sendCouponAfterRegisterError(user.getId());
        return "success";
    }

    public String registerCircuitOpen(User user) {
        System.out.println("feign,用户注册成功：" + user);
        activityService.sendCouponAfterRegisterCircuitOpen(user.getId());
        return "success";
    }

    public String sendCouponAfterRegisterFallback(User user){
        System.out.println("feign,发放优惠券降级，fallback");
        return "feign,发放优惠券降级，fallback";
    }
}
