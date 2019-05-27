package com.github.xjs.order_auto_confirm.service;

import com.github.xjs.order_auto_confirm.delay.BaseDelayed;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月24日 下午4:12:47<br/>
 */
public class DelayedOrder extends BaseDelayed<String>{
	
	/**默认过期时间是30分钟*/
	private static int timeout = 30 * 60;
	
	public DelayedOrder(){}
	
	public DelayedOrder(String orderId) {
		super(timeout, orderId);
	}
	
	public DelayedOrder(int timeoutSeconds, String orderId) {//TODO 测试用
		super(timeoutSeconds, orderId);
	}
	
}
