package com.github.xjs.order_auto_confirm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.xjs.order_auto_confirm.delay.DelayedService;
import com.github.xjs.order_auto_confirm.delay.OnDelayedListener;
import com.github.xjs.order_auto_confirm.entity.OrderEntity;
import com.github.xjs.order_auto_confirm.util.ThreadPoolUtil;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月24日 下午4:19:03<br/>
 */
@Service
public class OrderService implements InitializingBean	 {
	
	private static Logger log = LoggerFactory.getLogger(OrderService.class);
	
	private static final String ORDER_KEY = "orders"; 
	
	@Autowired
	DelayedService delayedService;
	
	@Autowired
	RedisService redisService;
	
	public void afterPropertiesSet() throws Exception{
		//系统启动，注册监听
		delayedService.registerListener(DelayedOrder.class, new OnDelayedListener<DelayedOrder>() {
			@Override
			public void onDelayedArrived(DelayedOrder order) {
				log.info("onDelayedArrived:{}", order.toString());
				//查询订单的状态
				int status = 0;//0代表未支付
				if(status == 0) {
					//自动取消订单
					autoCancelOrder(order);
				}else if(status == 1){//1代表已经支付
					//do nothing
				} else if(status == 2){//已经取消
					//do nothing
				}
			}
		});
		//把redis中的订单重新插入队列
		ThreadPoolUtil.execute(()->{
			List<DelayedOrder> orders = redisService.getFromSet(ORDER_KEY, DelayedOrder.class);
			if(orders != null && orders.size() > 0) {
				for(DelayedOrder order : orders) {
					delayedService.add(order);
				}
			}
		});
	}
	
	/**创建订单**/
	public DelayedOrder createOrder(String orderId) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setStatus(0);
		orderEntity.setOrderId(orderId);
		log.info("订单{}创建完成，插入数据库", orderId);
		DelayedOrder order = new DelayedOrder(60, orderId);
		delayedService.add(order);
		redisService.addToSet(ORDER_KEY, order);
		return order;
	}
	
	/**支付订单**/
	public boolean payOrder(String orderId) {
		OrderEntity orderEntity = getFromDb(orderId);
		orderEntity.setOrderId(orderId);
		orderEntity.setStatus(1);
		log.info("订单{}支付完成", orderId);
		DelayedOrder order = delayedService.getDelayed(DelayedOrder.class, orderId);
		if(order != null) {
			delayedService.remove(DelayedOrder.class, order);
			redisService.deleteFromSet(ORDER_KEY, order);
		}
		return true;
	}
	
	/**自动取消订单**/
	public boolean autoCancelOrder(DelayedOrder order) {
		//修改数据库状态
		String orderId = order.getValue();
		OrderEntity orderEntity = getFromDb(orderId);
		orderEntity.setOrderId(orderId);
		orderEntity.setStatus(2);
		log.info("修改订单{}为已经取消", orderId);
		delayedService.remove(DelayedOrder.class, order);
		redisService.deleteFromSet(ORDER_KEY, order);
		return true;
	}
	
	private OrderEntity getFromDb(String orderId) {
		OrderEntity entity = new OrderEntity();
		entity.setOrderId(orderId);
		return entity;
	}
	
}
