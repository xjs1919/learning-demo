package com.github.xjs.order_auto_confirm.delay;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.github.xjs.order_auto_confirm.util.ThreadPoolUtil;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月24日 下午4:07:41<br/>
 */
@Service
public class DelayedService implements  InitializingBean{
	
	private static Logger log = LoggerFactory.getLogger(DelayedService.class);

	private static DelayQueue<BaseDelayed<?>> delayQueue = new DelayQueue<BaseDelayed<?>>();
	
	@SuppressWarnings("rawtypes")
	private  static ConcurrentHashMap<Class<? extends BaseDelayed>, OnDelayedListener> listeners = new ConcurrentHashMap<Class<? extends BaseDelayed>, OnDelayedListener>();
	
	private static AtomicBoolean start = new AtomicBoolean(false);
	
	public void afterPropertiesSet() throws Exception{
		start();
	}
	
	public <T extends BaseDelayed<?>> void registerListener(Class<T> clazz, OnDelayedListener<T> listener) {
		if(clazz == null || listener == null) {
			return;
		}
		listeners.put(clazz, listener);
	}
	
	public <T> void start() {
		if (start.get()) {
			return;
		}
		start.set(true);
		// 启动DelayQueue
		new Thread(new Runnable() {
			@SuppressWarnings({"rawtypes", "unchecked"})
			public void run() {
				log.info("DelayedService start");
				try {
					while (true) {
						//从DelayQueue中取
						BaseDelayed<?> baseDelayed = delayQueue.take();
						OnDelayedListener  onDelayedListener = listeners.get(baseDelayed.getClass());
						if (onDelayedListener != null) {
							ThreadPoolUtil.execute(new Runnable() {
								public void run() {
									onDelayedListener.onDelayedArrived(baseDelayed);
								}
							});
						}else {//TODO
							log.warn("没有找到监听，丢弃：{}", baseDelayed.toString());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"DelayedService").start();
	}

	public void add(BaseDelayed<?> baseDelayed) {
		if(!start.get()) {
			throw new RuntimeException("DelayedService还没启动");
		}
		log.info("add:{}", baseDelayed.toString());
		delayQueue.put(baseDelayed);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  <T extends BaseDelayed, K>T getDelayed(Class<T> clazz, K key) {
		BaseDelayed<?>[] array = delayQueue.toArray(new BaseDelayed<?>[] {});
		if (array == null || array.length <= 0) {
			return null;
		}
		for (BaseDelayed<?> delayed : array) {
			if (delayed.getClass() != clazz) {
				continue;
			}
			if(delayed.getValue().equals(key)) {
				return (T)delayed;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T, D extends BaseDelayed<?>> D remove(Class<T> clazz , T value) {
		if(!start.get()) {
			throw new RuntimeException("DelayedService还没启动");
		}
		BaseDelayed<?>[] array = delayQueue.toArray(new BaseDelayed<?>[] {});
		if (array == null || array.length <= 0) {
			return null;
		}
		D target = null;
		for (BaseDelayed<?> delayed : array) {
			if(delayed.getClass() != clazz) {
				continue;
			}
			if (delayed.getValue().equals(value)) {
				target = (D) delayed;
				break;
			}
		}
		if (target != null) {
			delayQueue.remove(target);
		}
		return target;
	}
	
}