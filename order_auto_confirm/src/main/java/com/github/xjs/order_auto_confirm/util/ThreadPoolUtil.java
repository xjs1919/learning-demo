package com.github.xjs.order_auto_confirm.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月24日 下午4:09:10<br/>
 */
public class ThreadPoolUtil {
	
	private final ExecutorService executor;

	private static ThreadPoolUtil instance = new ThreadPoolUtil();

	private ThreadPoolUtil() {
		this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
	}

	public static ThreadPoolUtil getInstance() {
		return instance;
	}

	public static <T> Future<T> execute(final Callable<T> runnable) {
		return getInstance().executor.submit(runnable);
	}

	public static Future<?> execute(final Runnable runnable) {
		return getInstance().executor.submit(runnable);
	}

}
