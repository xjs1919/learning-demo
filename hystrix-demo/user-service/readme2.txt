调用不同的服务设置使用不同的线程池，舱壁模式，让异常的服务不会影响正常的服务
com.netflix.hystrix.HystrixThreadPoolProperties

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
    return restTemplate.postForObject("http://ACTIVITY-SERVICE/sendCouponAfterRegisterTimeout", userId, String.class);
}

@HystrixCommand(fallbackMethod = "sendCouponAfterRegisterFallback",
        threadPoolKey = "sendCouponAfterRegisterError",
        threadPoolProperties={
                @HystrixProperty(name="coreSize", value="2"),
                @HystrixProperty(name="maxQueueSize", value="20")
        })
public String sendCouponAfterRegisterError(Long userId) {
    return restTemplate.postForObject("http://ACTIVITY-SERVICE/sendCouponAfterRegisterError", userId, String.class);
}
多次调用registerTimeout和registerError方法，最终只有3个线程：
jstack 1296 | grep hystrix
"hystrix-sendCouponAfterRegisterTimeout-1" #57 daemon prio=5 os_prio=0 tid=0x000000001f2f6800 nid=0x3d90 waiting on condition [0x0000000024a8f000]
"hystrix-sendCouponAfterRegisterError-2" #52 daemon prio=5 os_prio=0 tid=0x000000001f2f8800 nid=0x1bc0 waiting on condition [0x000000002315f000]
"hystrix-sendCouponAfterRegisterError-1" #44 daemon prio=5 os_prio=0 tid=0x000000001f7ac800 nid=0x22dc waiting on condition [0x00000000234bf000]

当活动服务已经出现性能问题，能不能不去调用活动服务？
1.当调用出现错误的时候，开启一个时间窗口，默认10秒
2.在这个时间窗口以内，统计总的调用次数是否达到了最小请求数
    如果没有达到，重置统计信息，回到1，不管请求是否成功失败
    如果达到了，统计失败的请求占比是否达到阈值
        如果没有达到，重置统计信息，回到1
        如果达到了，则跳闸
3.跳闸以后，开启一个活动窗口，默认5秒，每隔5秒，hystrix会尝试去调用服务
    如果调用成功，重置断路器，回到1
    如果调用失败，回到3，继续下一个5秒

如何查看服务的状态：
1.添加依赖
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
2.添加配置
management:
  endpoint:
    health:
      show-details: always
  endpoints:
    web:
      exposure:
        include: "*"
3.访问：http://localhost:8200/actuator/health
里面就会有hystrix的状态
hystrix: {
    status: "UP"
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
    return restTemplate.postForObject("http://ACTIVITY-SERVICE/sendCouponAfterRegisterError", userId, String.class);
}

连续调用，则跳闸
hystrix: {
    status: "CIRCUIT_OPEN",
    details: {
        openCircuitBreakers: [
        "ActivityService::sendCouponAfterRegisterCircuitOpen"
        ]
    }
}
修改活动服务正常，3秒钟以后会去检查，首先调用一次活动服务，再看下health则变为正常了。



