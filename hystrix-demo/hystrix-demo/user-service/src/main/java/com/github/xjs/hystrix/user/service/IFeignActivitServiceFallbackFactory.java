
package com.github.xjs.hystrix.user.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Service;

@Service
public class IFeignActivitServiceFallbackFactory implements FallbackFactory<FeignActivitServiceClient> {
    @Override
    public FeignActivitServiceClient create(Throwable throwable) {
        return new FeignActivitServiceClient(){
            @Override
            public String sendCouponAfterRegister(Long userId) {
                System.out.println("IFeignActivitServiceFallbackFactory:sendCouponAfterRegister:"+throwable);
                return "IFeignActivitServiceFallbackFactory:sendCouponAfterRegister";
            }

            @Override
            public String sendCouponAfterRegisterTimeout(Long userId) {
                System.out.println("IFeignActivitServiceFallbackFactory:sendCouponAfterRegisterTimeout:"+throwable);
                return "IFeignActivitServiceFallbackFactory:sendCouponAfterRegisterTimeout";
            }

            @Override
            public String sendCouponAfterRegisterError(Long userId) {
                System.out.println("-->IFeignActivitServiceFallbackFactory:sendCouponAfterRegisterError:"+throwable);
                return "IFeignActivitServiceFallbackFactory:sendCouponAfterRegisterError";
            }

            @Override
            public String sendCouponAfterRegisterCircuitOpen(Long userId) {
                System.out.println("IFeignActivitServiceFallbackFactory:sendCouponAfterRegisterCircuitOpen:"+throwable);
                return "IFeignActivitServiceFallbackFactory:sendCouponAfterRegisterCircuitOpen";
            }
        };
    }
}
