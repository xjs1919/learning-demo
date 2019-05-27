package com.github.xjs.order_auto_confirm.entity;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月25日 下午4:53:21<br/>
 */
public class OrderEntity {
	private String orderId;
	private int status;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
