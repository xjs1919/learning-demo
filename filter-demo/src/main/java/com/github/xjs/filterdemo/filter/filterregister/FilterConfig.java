package com.github.xjs.filterdemo.filter.filterregister;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<Filter5> filter5() {
		FilterRegistrationBean<Filter5> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.setFilter(new Filter5());
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.setName("Filter5");
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean<Filter6> filter6() {
		FilterRegistrationBean<Filter6> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.setFilter(new Filter6());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setName("Filter6");
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
}