package com.xjs1919.mybatis;

import com.xjs1919.mybatis.config.AppConfig;
import com.xjs1919.mybatis.shard.DataSourceConfig;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author jiashuai.xujs
 * @date 2022/4/1 15:32
 */
@Configuration
@ServletComponentScan
@Import({DataSourceConfig.class, AppConfig.class})
public class ShardConfiguration {

}
