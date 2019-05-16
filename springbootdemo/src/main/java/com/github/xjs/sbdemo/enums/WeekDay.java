package com.github.xjs.sbdemo.enums;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月11日 上午10:46:46<br/>
 */
public class WeekDay extends BaseEnum<Integer> {
	public WeekDay(Integer value, String desc) {
		super(value, desc);
	}
	public static WeekDay MONDAY = new WeekDay(1,"星期一");
	public static WeekDay TUESDAY = new WeekDay(2,"星期二");
	public static WeekDay WEDNESDAY = new WeekDay(3,"星期三");
	public static WeekDay THURSDAY = new WeekDay(4,"星期四");
	public static WeekDay FRIDY = new WeekDay(5,"星期五");
	public static WeekDay SATURDAY = new WeekDay(6,"星期六");
	public static WeekDay SUNDAY = new WeekDay(7,"星期天");
}
