package com.github.xjs.spdemo.dto;

import com.github.xjs.spdemo.enums.WeekDay;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月14日 上午9:44:55<br/>
 */
public class DayDto{
	private WeekDay weekDay;
	private String whatever;
	
	public WeekDay getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(WeekDay weekDay) {
		this.weekDay = weekDay;
	}
	public String getWhatever() {
		return whatever;
	}
	public void setWhatever(String whatever) {
		this.whatever = whatever;
	}
	@Override
	public String toString() {
		return "DayDto [weekDay=" + weekDay + ", whatever=" + whatever + "]";
	}
	
}