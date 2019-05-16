package com.github.xjs.sbdemo.enums;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月16日 下午1:30:35<br/>
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new EnumConverterFactory());
    }
	
}
