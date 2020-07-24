package com.github.xjs.hystrix.activity.service;

import com.github.xjs.hystrix.activity.constants.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public interface IActivityApiService {

    public String sendCouponAfterRegister(Long userId);

    public String sendCouponAfterRegisterTimeout(Long userId);

    public String sendCouponAfterRegisterError(Long userId);

    public String sendCouponAfterRegisterCircuitOpen(Long userId) ;

}
