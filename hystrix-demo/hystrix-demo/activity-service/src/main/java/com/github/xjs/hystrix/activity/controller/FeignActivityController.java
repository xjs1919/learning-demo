package com.github.xjs.hystrix.activity.controller;

import com.github.xjs.hystrix.activity.feign.IFeignActivitService;
import com.github.xjs.hystrix.activity.service.IActivityApiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignActivityController implements IFeignActivitService {

    public String sendCouponAfterRegister(@RequestBody Long userId){
        System.out.println("feign,用户"+userId+"注册成功以后，发放优惠券");
        return "发放优惠券成功,feign";
    }


    public String sendCouponAfterRegisterTimeout(@RequestBody Long userId){
        try{
            Thread.sleep(10000);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("feign,用户"+userId+"注册成功以后，发放优惠券");
        return "发放优惠券成功";
    }

    public String sendCouponAfterRegisterError(@RequestBody Long userId){
        throw new RuntimeException("feign,发放优惠券出错");
        // return "发放优惠券成功";
    }

    public String sendCouponAfterRegisterCircuitOpen(Long userId) {
        return sendCouponAfterRegisterError(userId);
    }

}
