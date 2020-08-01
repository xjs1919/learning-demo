package com.github.xjs.hystrix.user.service;

import com.github.xjs.hystrix.activity.service.IActivityApiService;
import com.github.xjs.hystrix.user.vo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    //private ActivityService activityService;
    //private ActivityApiService activityService;
    private IActivityApiService activityService;

    public String register(User user) {
        System.out.println("用户注册成功：" + user);
        activityService.sendCouponAfterRegister(user.getId());
        return "success";
    }

    @HystrixCommand(
            threadPoolKey = "sendCouponAfterRegisterTimeout",
            threadPoolProperties={
                    @HystrixProperty(name="coreSize", value="1"),
                    @HystrixProperty(name="maxQueueSize", value="10")
            },
            commandProperties={
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000")
            })
    public String registerTimeout(User user) {
        System.out.println("用户注册成功：" + user);
        activityService.sendCouponAfterRegisterTimeout(user.getId());
        return "success";
    }

    @HystrixCommand(fallbackMethod = "sendCouponAfterRegisterFallback",
            threadPoolKey = "sendCouponAfterRegisterError",
            threadPoolProperties={
                    @HystrixProperty(name="coreSize", value="2"),
                    @HystrixProperty(name="maxQueueSize", value="20")
            })
    public String registerError(User user) {
        System.out.println("用户注册成功：" + user);
        activityService.sendCouponAfterRegisterError(user.getId());
        return "success";
    }

    /**
     * 5秒鐘以内，请求次数达到4个以上，失败率达到50%以上，则跳闸
     * 跳闸后的活动窗口为3秒
     * */
    @HystrixCommand(
            commandProperties={
                    @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="5000"),
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="4"),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="3000")
            }
    )
    public String registerCircuitOpen(User user) {
        System.out.println("用户注册成功：" + user);
        activityService.sendCouponAfterRegisterCircuitOpen(user.getId());
        return "success";
    }

    public String sendCouponAfterRegisterFallback(User user){
        System.out.println("发放优惠券降级，fallback");
        return "发放优惠券降级，fallback";
    }
}
