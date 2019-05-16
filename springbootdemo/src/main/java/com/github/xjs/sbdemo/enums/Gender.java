package com.github.xjs.sbdemo.enums;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月14日 下午2:30:39<br/>
 */
public class Gender extends BaseEnum<Integer> {

	public Gender(Integer value, String desc) {
		super(value, desc);
	}
	public static Gender MALE = new Gender(1, "男");
	public static Gender FEMALE = new Gender(2, "女");
	

}
