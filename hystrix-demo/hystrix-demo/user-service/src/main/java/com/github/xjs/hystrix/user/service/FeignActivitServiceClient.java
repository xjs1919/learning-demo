package com.github.xjs.hystrix.user.service;

import com.github.xjs.hystrix.activity.feign.IFeignActivitService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="activity-service", fallbackFactory = IFeignActivitServiceFallbackFactory.class)
public interface FeignActivitServiceClient extends IFeignActivitService {

}
