package com.github.xjs.springboot4.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/{id}")
public class AccountController {

	@GetMapping
	public Account getAccount() {
		return new Account("any version");
	}

	@GetMapping(version = "1.1")
	public Account getAccount1_1() {
		return new Account("exactly version 1.1");
	}

	@GetMapping(version = "1.2+")
	public Account getAccount1_2() {
		return new Account("baseline version 1.2 and above");
	}

	@GetMapping(version = "1.5")
	public Account getAccount1_5() {
		return new Account("exactly version 1.5");
	}

	@GetMapping(version = "1.5.0.1")
	public Account getAccount1_5_0_1() {
		return new Account("exactly version 1.5.0.1");
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class Account{
		private String name;
	}
}