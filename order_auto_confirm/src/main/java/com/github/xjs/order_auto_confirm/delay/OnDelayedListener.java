package com.github.xjs.order_auto_confirm.delay;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月25日 下午3:09:09<br/>
 */
@SuppressWarnings("rawtypes")
public interface OnDelayedListener<T extends BaseDelayed> {
	public void onDelayedArrived(T delayed);
}
