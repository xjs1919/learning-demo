package com.github.xjs.profiledemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author xujs@mamcharge.com
 * @Date 2019/9/3 17:59
 **/
@RestController
public class ProfileController {

    @Autowired
    MyProperties myProperties;

    @GetMapping("/")
    public String getProps(){
       return myProperties.getUsername() + "," +myProperties.getPassword();
    }
    //mvn clean package -Pdev -DskipTests
    //java -jar profile-demo-0.0.1-SNAPSHOT.jar 这里不需要再设置profile

}
