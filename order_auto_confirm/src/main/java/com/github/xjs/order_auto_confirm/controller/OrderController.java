package com.github.xjs.order_auto_confirm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.xjs.order_auto_confirm.service.DelayedOrder;
import com.github.xjs.order_auto_confirm.service.OrderService;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月24日 下午4:06:02<br/>
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/create")
	public String createOrder(@RequestParam("orderId")String orderId) {
		DelayedOrder order = orderService.createOrder(orderId);
		return order.getValue();
	}
	
	@GetMapping("/pay")
	public Boolean payOrder(@RequestParam("orderId")String orderId) {
		boolean ret = orderService.payOrder(orderId);
		return ret;
	}

}
