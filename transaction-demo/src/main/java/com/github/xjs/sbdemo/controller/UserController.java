package com.github.xjs.sbdemo.controller;

import com.github.xjs.sbdemo.entity.User;
import com.github.xjs.sbdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {


	@Autowired
	private UserService userService;

	
	@GetMapping(value="/register")
	public int register() {
		return userService.register();
	}

	@GetMapping("/all")
	public List<User> all(){
		return userService.all();
	}

}
