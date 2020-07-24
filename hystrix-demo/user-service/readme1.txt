1.用户服务直接调用活动服务
curl localhost:8200/register -X POST -H 'content-type':'application/json;charset=utf-8' -d '{"id":10,"name":"zhangsan"}'

我们希望：
（1）当活动服务出现性能问题的时候，用户服务不会被拖垮
（2）当活动服务异常的时候，用户服务可以正常注册，活动可以有备选的降级方案

解决方案
（1）当活动服务响应时间过长的时候，用户服务不在等待
（2）提供备用方案，当活动不可用的时候，启用备用方案

2.用户服务调用活动服务超时，模拟服务很慢，出现性能问题
com.netflix.hystrix.HystrixCommandProperties
execution.isolation.thread.timeoutInMilliseconds: 服务的超时时间，超过就不再等待
curl localhost:8200/registerTimeout -X POST -H 'content-type':'application/json;charset=utf-8' -d '{"id":10,"name":"zhangsan"}'
{"timestamp":"2020-07-24T08:22:43.594+0000","status":500,"error":"Internal Server Error","message":"sendCouponAfterRegisterTimeout timed-out and fallback failed.","path":"/registerTimeout"}

@HystrixCommand(commandProperties={
        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000")
})
public String sendCouponAfterRegisterTimeout(Long userId) {
    return restTemplate.postForObject("http://ACTIVITY-SERVICE/sendCouponAfterRegisterTimeout", userId, String.class);
}

3.活动服务出问题的时候，启用降级的备用方案
@HystrixCommand(fallbackMethod = "sendCouponAfterRegisterFallback")
public String sendCouponAfterRegisterError(Long userId) {
    return restTemplate.postForObject("http://ACTIVITY-SERVICE/sendCouponAfterRegisterError", userId, String.class);
}
public String sendCouponAfterRegisterFallback(Long userId){
    System.out.println("发放优惠券降级，fallback");
    return "发放优惠券降级，fallback";
}



