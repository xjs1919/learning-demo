package com.github.xjs.spdemo.enums;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月11日 上午10:41:04<br/>
 */
public abstract class BaseEnum<T> implements EnumAble<T>{
	private T value;
	private String desc;
	public BaseEnum(T value, String desc) {
		this.value = value;
		this.desc = desc;
		//添加这一行
		EnumFactory.add(this);
	}
	@Override
	public T getValue() {
		return this.value;
	}
	@Override
	public String getDesc() {
		return this.desc;
	}
	//添加这个方法
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[value=" + value + ", desc=" + desc + "]";
	}
}
