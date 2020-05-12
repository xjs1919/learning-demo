package com.github.xjs.eventdemo;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class CouponUserRegisterListener {

    @EventListener
    public void sendCoupon(UserRegisterEvent event) {
        System.out.println(Thread.currentThread().getName()+"-给用户"+event.getUsername()+"发送优惠券!");
    }

}
