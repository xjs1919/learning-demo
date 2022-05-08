springmvc:
https://docs.spring.io/spring-framework/docs/current/reference/html/web.html
mybatis-spring:
https://mybatis.org/spring/index.html
copy
copy /y "mybatis-springmvc\target\mybatis-springmvc.war" D:\ali-tomcat-8.5.51\webapps\

(1)添加依赖
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis-spring</artifactId>
  <version>2.0.7</version>
</dependency>
（2）定义SqlSessionFactory
