package com.github.xjs.sbdemo.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月16日 下午2:09:58<br/>
 */
@org.springframework.context.annotation.Configuration
public class EnumSqlSessionFactoryConfig {

	@Autowired
	private MybatisProperties properties;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	 ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider;

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		EnumSqlSessionFactory factory = new EnumSqlSessionFactory();
		factory.setDataSource(dataSource);
		if (StringUtils.hasText(this.properties.getConfigLocation())) {
			factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
		}
		Configuration configuration = this.properties.getConfiguration();
		if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
			configuration = new Configuration();
		}
		List<ConfigurationCustomizer> configurationCustomizers = this.configurationCustomizersProvider.getIfAvailable();
		if (configuration != null && !CollectionUtils.isEmpty(configurationCustomizers)) {
			for (ConfigurationCustomizer customizer : configurationCustomizers) {
				customizer.customize(configuration);
			}
		}
		if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
			factory.setMapperLocations(this.properties.resolveMapperLocations());
		}
		return factory.getObject();
	}
}
