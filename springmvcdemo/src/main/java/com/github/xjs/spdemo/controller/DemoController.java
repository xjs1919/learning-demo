package com.github.xjs.spdemo.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.xjs.spdemo.dto.DayDto;
import com.github.xjs.spdemo.enums.WeekDay;


/**
 * @author xujs@inspur.com
 *
 * @date 2017年9月14日 下午4:32:17
 */
@RestController
@RequestMapping(value = "/demo")
public class DemoController{
	
	private static Logger log = LoggerFactory.getLogger(DemoController.class);
	
	@RequestMapping(value="/wd", method=RequestMethod.GET)
	@ResponseBody
	public WeekDay wd(WeekDay weekDay) throws Exception{
		log.info(weekDay.toString());
		return weekDay;
	}
	@RequestMapping(value="/dto1", method=RequestMethod.POST)
	 @ResponseBody
	public DayDto dto1(DayDto dto) throws Exception{
		log.info(dto.toString());
		return dto;
	}
	@RequestMapping(value="/dto2", method=RequestMethod.POST)
	 @ResponseBody
	public DayDto dto2(@RequestBody DayDto dto) throws Exception{
		log.info(dto.toString());
		return dto;
	}
	
}