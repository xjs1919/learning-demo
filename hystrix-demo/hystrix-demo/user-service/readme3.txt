目前存在的问题：
1.服务地址是字符串，容易出错
2.服务的入参和返回值是在客户端写的，不容易与服务端保持一致
3.服务提供者发生了变化，如何通知到调用者
解决方案：
1.把服务地址抽取成常量
2.让服务端提供调用接口，客户端只负责引用
3.抽取接口，让服务提供者 和 api都实现接口

1.抽取常量放到 activity-api
2.在activity-api里面：
    2.1 提供ActivityApiService，里面做服务调用
    2.2 提供AutoConfiguration，注册ActivityApiService
    2.3 用户服务引用ActivityApiService
    2.4 此时，hystrix的设置就不能放在activity-api，而是放在服务调用方里面
3.

