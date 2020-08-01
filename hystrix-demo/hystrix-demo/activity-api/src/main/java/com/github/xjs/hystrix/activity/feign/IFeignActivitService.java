package com.github.xjs.hystrix.activity.feign;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/feign")
public interface IFeignActivitService {

    @PostMapping("/sendCouponAfterRegister")
    public String sendCouponAfterRegister(@RequestBody Long userId);

    @PostMapping("/sendCouponAfterRegisterTimeout")
    public String sendCouponAfterRegisterTimeout(@RequestBody Long userId);

    @PostMapping("/sendCouponAfterRegisterError")
    public String sendCouponAfterRegisterError(@RequestBody Long userId);

    @PostMapping("/sendCouponAfterRegisterCircuitOpen")
    public String sendCouponAfterRegisterCircuitOpen(Long userId) ;
}
