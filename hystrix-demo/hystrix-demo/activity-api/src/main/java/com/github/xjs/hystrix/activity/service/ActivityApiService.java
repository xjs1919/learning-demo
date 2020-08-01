package com.github.xjs.hystrix.activity.service;

import com.github.xjs.hystrix.activity.constants.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConditionalOnBean(RestTemplate.class)
public class ActivityApiService implements IActivityApiService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String sendCouponAfterRegister(Long userId) {
        return restTemplate.postForObject(Constant.register, userId, String.class);
    }

    @Override
    public String sendCouponAfterRegisterTimeout(Long userId) {
        return restTemplate.postForObject(Constant.timeout, userId, String.class);
    }

    @Override
    public String sendCouponAfterRegisterError(Long userId) {
        return restTemplate.postForObject(Constant.error, userId, String.class);
    }

    @Override
    public String sendCouponAfterRegisterCircuitOpen(Long userId) {
        return restTemplate.postForObject(Constant.error, userId, String.class);
    }


}
