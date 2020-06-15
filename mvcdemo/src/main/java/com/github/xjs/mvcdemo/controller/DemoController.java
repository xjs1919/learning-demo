package com.github.xjs.mvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/demo")
public class DemoController{

	@GetMapping("/simple")
	@ResponseBody
	public String simple(){
		return "world";
	}

	@GetMapping("/map")
	@ResponseBody
	public Map<String, String> map(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("hello","world");
		return map;
	}


	@GetMapping("/home")
	@ResponseBody
	public ModelAndView home(){
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("message", "this is a message");
		return mav;
	}

}