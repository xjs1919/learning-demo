如何做监控？单个服务 与 聚合服务
监控单个服务
（1）添加依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>
（2）添加注解@EnableHystrixDashboard

被监控的服务需要满足：
1.项目中添加了hystrix
2.项目中添加了actuator
3.配置暴露hystrix那个端点，然后就会有/hystrix.stream
访问：http://localhost:8200/actuator/hystrix.stream 就会有统计数据

访问dashboard的url：http://localhost:8500/hystrix，输入要监控的服务的端点url:
http://localhost:8200/actuator/hystrix.stream
展示可视化数据

监控多个节点上的聚合服务
(1)再启动一个userservice服务8600
(2)添加turbine的依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-turbine</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
（3）添加注解
@EnableTurbine
（4）添加配置
eureka:
  instance:
    preferIpAddress: true
  client:
    register-with-eureka: true
    service-url:
      defaultZone: http://localhost:8000/eureka/

turbine:
 appConfig: user-service #这个是要聚合的服务，多个用逗号分隔
 clusterNameExpression: "'default'"   # use “default” cluster for all apps

 （5）在dashboard中配置：
 http://localhost:8700/turbine.stream







