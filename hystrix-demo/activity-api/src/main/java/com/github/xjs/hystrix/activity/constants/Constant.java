package com.github.xjs.hystrix.activity.constants;

public interface Constant {

    String prefix= "http://ACTIVITY-SERVICE";
    String register = prefix + "/sendCouponAfterRegister";
    String timeout = prefix + "/sendCouponAfterRegisterTimeout";
    String error = prefix + "/sendCouponAfterRegisterError";

}
