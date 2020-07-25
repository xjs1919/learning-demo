目前存在的问题：
服务提供者同时需要提供服务，还要提供供调用的api，提供者的任务会比较多！
引入feign
1.添加依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
2.添加注解
@EnableFeignClients
3.@FeignClient(name="activity-service")
public interface IFeignActivitService {
    @PostMapping("/sendCouponAfterRegister")
    public String sendCouponAfterRegister(@RequestBody Long userId);
}
集成hystrix
1.添加配置
feign:
  hystrix:
    enabled: true
2.添加fallback

如何想知道异常的原因，可以fallbackFactory

开启hystrix的高级特性
1.添加配置
hystrix:
  command:
    default:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 1000
default：是全局配置，如果是配置某一个方法，那么属性名是：
(feign.Feign#configKey()方法)
hystrix:
  command:
    IFeignActivitService#sendCouponAfterRegisterTimeout(Long):
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 10

目前存在冗余，可以优化下
feign接口和被调用方的controller的签名是一样的，url写了两份
解决方案：
（1）把feignClient接口移动到api模块中,删除feign相关的注解，api模块不依赖springcloud
（2）让服务提供方的controller实现feignClient接口，controller上的mapping就可以去掉了
（3）服务调用放定义一个新的client注解，继承feignClient接口，在这个client上添加feign的注解
(1)api模块中的接口
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
（2）提供方的controller（不再需要定义mapping），实现api接口
@RestController
public class FeignActivityController implements IFeignActivitService {
}
（3）调用方，实现api接口，定义降级错略等等
@FeignClient(name="activity-service", fallbackFactory = IFeignActivitServiceFallbackFactory.class)
public interface FeignActivitServiceClient extends IFeignActivitService {
}
perfect！









