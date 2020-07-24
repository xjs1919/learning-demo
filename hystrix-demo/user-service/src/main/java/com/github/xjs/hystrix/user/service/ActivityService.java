package com.github.xjs.hystrix.user.service;

import com.github.xjs.hystrix.activity.constants.Constant;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ActivityService {

    @Autowired
    private RestTemplate restTemplate;

    public String sendCouponAfterRegister(Long userId) {
        return restTemplate.postForObject(Constant.register, userId, String.class);
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
    public String sendCouponAfterRegisterTimeout(Long userId) {
        return restTemplate.postForObject(Constant.timeout, userId, String.class);
    }

    @HystrixCommand(fallbackMethod = "sendCouponAfterRegisterFallback",
            threadPoolKey = "sendCouponAfterRegisterError",
            threadPoolProperties={
                    @HystrixProperty(name="coreSize", value="2"),
                    @HystrixProperty(name="maxQueueSize", value="20")
            })
    public String sendCouponAfterRegisterError(Long userId) {
        return restTemplate.postForObject(Constant.error, userId, String.class);
    }

    public String sendCouponAfterRegisterFallback(Long userId){
        System.out.println("发放优惠券降级，fallback");
        return "发放优惠券降级，fallback";
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
    public String sendCouponAfterRegisterCircuitOpen(Long userId) {
        return restTemplate.postForObject(Constant.error, userId, String.class);
    }


}
