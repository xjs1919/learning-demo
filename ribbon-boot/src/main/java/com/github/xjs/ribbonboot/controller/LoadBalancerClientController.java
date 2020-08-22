package com.github.xjs.ribbonboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/8/22 14:19
 */
@RestController
public class LoadBalancerClientController {

//    @Autowired
//	private LoadBalancerClient loadBalancer;
//
//	@GetMapping("/loadBalancerClient")
//	public String manual(){
//		ServiceInstance serviceInstance = loadBalancer.choose("userService");
//		URI uri = serviceInstance.getUri();
//		return uri.toString();
//	}
}
