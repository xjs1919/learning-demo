package com.github.xjs.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;

/**
 * 嵌入式数据库h2
 */
@Slf4j
@Configuration
//DataSource创建完后才初始化此类
@AutoConfigureAfter(DataSource.class)
public class H2DataSourceConfig {

    //初始化sql
    private static final String schema="classpath:db/schema-h2.sql";

    @Autowired
    DataSource dataSource;

    @Autowired
    ApplicationContext applicationContext;


    @PostConstruct
    public  void init() throws Exception {
        //初始化本地数据库
        String userHome= System.getProperty("user.home");//获取系统用户目录
        File f = new File(userHome+File.separator+"h2.lck");
        if(!f.exists()){
            log.info("--------------初始化h2数据----------------------");
            f.createNewFile();
            Resource resource= applicationContext.getResource(schema);
            ScriptUtils.executeSqlScript(dataSource.getConnection(),resource);
        }else{
            log.info("--------------h2数据库已经存在----------------------");
        }
    }
}