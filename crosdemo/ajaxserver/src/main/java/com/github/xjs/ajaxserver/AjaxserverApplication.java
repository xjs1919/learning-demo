package com.github.xjs.ajaxserver;

import com.github.xjs.ajaxserver.filter.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AjaxserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AjaxserverApplication.class, args);
	}
	//@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter>  filterRegistrationBean = new FilterRegistrationBean<CorsFilter>();
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setFilter(new CorsFilter());
		return filterRegistrationBean;
	}
}
